package com.projectnt.security.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedAttemptError {
    public static ResponseStatusException create(){
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to perform this operation.");
    }
}
