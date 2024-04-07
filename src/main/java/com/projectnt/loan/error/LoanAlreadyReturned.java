package com.projectnt.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyReturned {
    public static ResponseStatusException create(long loanId){
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Loan with id: %s is already returned.",loanId));
    }
}
