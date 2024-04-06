package com.projectnt.user;

import com.projectnt.security.auth.AuthEntity;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.dto.*;
import com.projectnt.user.error.UserAlreadyExists;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.userRepository = userRepository;
    }

    public GetUserPagesDto getAll(int page, int size){
        Page<UserEntity> usersPage;
        Pageable pageable= PageRequest.of(page,size);

        usersPage= userRepository.findAll(pageable);
        List<GetUserDto> userDto= usersPage.getContent().stream().map(this::mapUser).toList();
        return new GetUserPagesDto(userDto,usersPage.getNumber(),usersPage.getTotalElements(),usersPage.getTotalPages(),usersPage.hasNext());
    }

    public GetUserDto getOneById(long user_id){
        var user= userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("User not found"));
        return mapUser(user);
    }

    public GetUserDto mapUser(UserEntity user){
        return new GetUserDto(user.getUserId(),user.getLastName(),user.getEmail(),user.getName());
    }

    public CreateUserResponseDto create(CreateUserDto user){
        Optional<UserEntity> existingEmail= userRepository.findByEmail(user.getEmail());

        if(existingEmail.isPresent()){
            throw UserAlreadyExists.createWithEmail(user.getEmail());
        }

        var userEntity= new UserEntity();
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());

        var newUser= userRepository.save(userEntity);

        return new CreateUserResponseDto(newUser.getUserId(),newUser.getLastName(),user.getEmail(),user.getName());
    }

    public void delete(long user_id){
        if(!userRepository.existsById(user_id)){
            throw UserDoesntExist.createWithId(user_id);
        }
        userRepository.deleteById(user_id);
    }

    public GetUserDto getUserByUsername(String username){
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserDoesntExist.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getUserId(),user.getEmail(),user.getName(),user.getLastName());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public PatchUserResponseDto update(long userId, PatchUserDto dto){
        UserEntity user= userRepository.findById(userId).orElseThrow(()-> UserDoesntExist.createWithId(userId));

        dto.getName().ifPresent(user::setName);
        dto.getLastname().ifPresent(user::setLastName);
        dto.getEmail().ifPresent(user::setEmail);

        userRepository.save(user);
        return new PatchUserResponseDto(user.getUserId(),user.getName(),user.getLastName(), user.getEmail());
    }

}
