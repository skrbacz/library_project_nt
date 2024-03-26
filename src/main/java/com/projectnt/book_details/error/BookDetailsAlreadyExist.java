package com.projectnt.book_details.error;

import com.projectnt.book.BookEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDetailsAlreadyExist {
    public static ResponseStatusException create(BookEntity book){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Details to this book: \" %s \" already exist.",book.getTitle()));
    }
}
