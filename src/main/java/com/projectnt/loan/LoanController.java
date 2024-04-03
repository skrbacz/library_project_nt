package com.projectnt.loan;

import com.projectnt.loan.dto.*;
import com.projectnt.review.dto.PatchReviewDto;
import com.projectnt.review.dto.PatchReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("isAuthenticated() ")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController (LoanService loanService){this.loanService= loanService;}

    @GetMapping
    public ResponseEntity<GetLoansPageResponseDto> getAllLoans(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetLoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{loan_id}")
    public ResponseEntity<GetLoanDto> getOne(@PathVariable long loan_id){
       GetLoanDto dto= loanService.getOneById(loan_id);
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{loan_id}")
    public ResponseEntity<PatchLoanResponseDto> update(@PathVariable long loan_id, @RequestBody PatchLoanDto dto){
        PatchLoanResponseDto responseDto= loanService.update(loan_id, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loanDto){
        var newLoan= loanService.create(loanDto);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{loan_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long loan_id){
        loanService.delete(loan_id);
        return ResponseEntity.noContent().build();
    }
}
