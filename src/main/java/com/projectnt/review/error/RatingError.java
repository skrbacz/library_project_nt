package com.projectnt.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RatingError {

    public static ResponseStatusException outOfBounds(){
        return new ResponseStatusException(HttpStatus.CONFLICT, "Please rate the book in scale 0-5");
    }

}


