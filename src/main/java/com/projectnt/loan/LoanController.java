package com.projectnt.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController (LoanService loanService){this.loanService= loanService;}

    @GetMapping
    public List<LoanEntity> getAllLoans(){return loanService.getAll();}

    @GetMapping("/{loan_id}")
    public LoanEntity getOne(@PathVariable long loan_id){
        return loanService.getOne(loan_id);
    }

    @PostMapping
    public ResponseEntity<LoanEntity> create(@RequestBody LoanEntity loan){
        var newLoan= loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{loan_id}")
    public ResponseEntity<Void> delete(@PathVariable long loan_id){
        loanService.delete(loan_id);
        return ResponseEntity.noContent().build();
    }
}
