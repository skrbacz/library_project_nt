package com.projectnt.book.error_or_message;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDoesntExist {
    public static ResponseStatusException createWIthId(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id: %s doesn't exist.",id));
    }
}
