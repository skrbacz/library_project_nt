package com.projectnt.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewDoesntExist {
    public static ResponseStatusException createWithId(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Review with id: %s doesn't exist.",id));
    }
}
