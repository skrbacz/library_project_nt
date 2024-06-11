package com.projectnt.user;

import com.projectnt.security.auth.AuthEntity;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.dto.*;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.userRepository = userRepository;
    }

    public UserPagesDto getAll(int page, int size){
        Page<UserEntity> usersPage;
        Pageable pageable= PageRequest.of(page,size);

        usersPage= userRepository.findAll(pageable);
        List<UserDto> userDto= usersPage.getContent().stream().map(this::mapUser).toList();
        return new UserPagesDto(userDto,usersPage.getNumber(),usersPage.getTotalElements(),usersPage.getTotalPages(),usersPage.hasNext());
    }

    public UserDto getOneById(long userId){
        var user= userRepository.findById(userId).orElseThrow(()-> UserDoesntExist.createWithId(userId));
        return mapUser(user);
    }

    public UserDto mapUser(UserEntity user){
        var auth= authRepository.findByUserUserId(user.getUserId());
        return new UserDto(user.getUserId(),user.getEmail(),user.getName(),user.getLastName(),auth.getUsername(),auth.getRole().toString());
    }


    public void delete(Long userId){
        if(!userRepository.existsById(userId)){
            throw UserDoesntExist.createWithId(userId);
        }
        authRepository.deleteById(userId);
        userRepository.deleteById(userId);

    }

    public UserDto getUserByUsername(String username){
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserDoesntExist.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new UserDto(user.getUserId(),user.getEmail(),user.getName(),user.getLastName(), username, auth.getRole().toString());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public UpdateUserResponseDto update(long userId, UpdateUserDto dto){
        UserEntity user= userRepository.findById(userId).orElseThrow(()-> UserDoesntExist.createWithId(userId));

        dto.getName().ifPresent(user::setName);
        dto.getLastname().ifPresent(user::setLastName);
        dto.getEmail().ifPresent(user::setEmail);

        userRepository.save(user);
        return new UpdateUserResponseDto(user.getUserId(),user.getName(),user.getLastName(), user.getEmail());
    }

}
