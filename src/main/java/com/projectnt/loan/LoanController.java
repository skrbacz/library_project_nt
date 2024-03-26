package com.projectnt.loan;

import com.projectnt.book.dto.GetBookDto;
import com.projectnt.loan.dto.CreateLoanDto;
import com.projectnt.loan.dto.CreateLoanResponseDto;
import com.projectnt.loan.dto.GetLoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasRole('ADMIN')")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController (LoanService loanService){this.loanService= loanService;}

    @GetMapping
    public List<GetLoanDto> getAllLoans(){return loanService.getAll();}

    @GetMapping("/{loan_id}")
    public ResponseEntity<GetLoanDto> getOne(@PathVariable long loan_id){
       GetLoanDto dto= loanService.getOne(loan_id);
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan){
        var newLoan= loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{loan_id}")
    public ResponseEntity<Void> delete(@PathVariable long loan_id){
        loanService.delete(loan_id);
        return ResponseEntity.noContent().build();
    }
}
