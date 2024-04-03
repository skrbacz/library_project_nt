package com.projectnt.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanDoesntExist {
    public static ResponseStatusException createWithId(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Loan with id: %s doesn't exist.",id));
    }
}
