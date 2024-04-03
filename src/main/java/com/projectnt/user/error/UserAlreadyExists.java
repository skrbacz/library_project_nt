package com.projectnt.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists {
     public static ResponseStatusException createByUsermane(String username){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username: %s already exists.",username));
    }
    public static ResponseStatusException createByEmail(String email){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email: %s already exists.",email));
    }
}
