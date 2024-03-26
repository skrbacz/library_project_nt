package com.projectnt.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyUsed {
    public static ResponseStatusException create(String email){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email: %s already exists.",email));
    }
}
