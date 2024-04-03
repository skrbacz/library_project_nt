package com.projectnt.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserDoesntExist {
    public static ResponseStatusException createWithId(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id: %s doesn't exist.",id));
    }

    public static ResponseStatusException createWithUsername(String username){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username: %s doesn't exist.",username));
    }




}
