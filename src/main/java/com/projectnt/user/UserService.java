package com.projectnt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public UserEntity getOne(long user_id){
        return userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public UserEntity create(UserEntity user){
        return userRepository.save(user);
    }

    public void delete(long user_id){
        if(!userRepository.existsById(user_id)){
            throw new RuntimeException();
        }
        userRepository.deleteById(user_id);
    }

}
