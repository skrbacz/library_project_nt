package com.projectnt.security.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthError {
    public static ResponseStatusException createPass(){
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password. Try again");
    }
}
