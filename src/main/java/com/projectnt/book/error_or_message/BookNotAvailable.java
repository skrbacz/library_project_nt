package com.projectnt.book.error_or_message;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotAvailable {
    public static ResponseStatusException createWIthId(long id){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Book with id: %s is not available.",id));
    }
}
