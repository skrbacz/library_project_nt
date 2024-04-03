package com.projectnt.book_details.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDetailsDontExist {
    public static ResponseStatusException createWithId(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book details with id: %s don't exist.", id));
    }
}
