package com.projectnt.loan.error;

import com.projectnt.loan.LoanEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyExist {
    public static ResponseStatusException create(){
        return new ResponseStatusException(HttpStatus.CONFLICT, "This loan already exists.");
    }
}

