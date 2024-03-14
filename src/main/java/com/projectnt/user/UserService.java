package com.projectnt.user;

import com.projectnt.user.dto.CreateUserDto;
import com.projectnt.user.dto.CreateUserResponseDto;
import com.projectnt.user.dto.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetUserDto> getAll(){
        var users= userRepository.findAll();
        return users.stream().map((user)-> new GetUserDto(user.getUserId(),user.getUsername(),user.getPassword(),user.getRole(),user.getEmail(),user.getName())).collect(Collectors.toList());
    }

    public GetUserDto getOne(long user_id){
        var user= userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("User not found"));
        return new GetUserDto(user.getUserId(),user.getUsername(),user.getPassword(),user.getRole(),user.getEmail(),user.getName());
    }

    public CreateUserResponseDto create(CreateUserDto user){
        var userEntity= new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());

        var newUser= userRepository.save(userEntity);

        return new CreateUserResponseDto(newUser.getUserId(),newUser.getUsername(),newUser.getPassword(),user.getRole(),user.getEmail(),user.getName());
    }

    public void delete(long user_id){
        if(!userRepository.existsById(user_id)){
            throw new RuntimeException();
        }
        userRepository.deleteById(user_id);
    }

}
