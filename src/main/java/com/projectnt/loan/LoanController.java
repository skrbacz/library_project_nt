package com.projectnt.loan;

import com.projectnt.loan.dto.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController (LoanService loanService){this.loanService= loanService;}


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<LoansPageResponseDto> getAllLoans(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        LoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{loanId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "401", description = "Loan not found", content = @Content())
    })
    public ResponseEntity<LoanDto> getOne(@PathVariable long loanId){
       LoanDto dto= loanService.getOneById(loanId);
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan successfully created"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loanDto){
        var newLoan= loanService.create(loanDto);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{loanId}")
    @ApiResponse(responseCode = "200", description = "Loan deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable long loanId){
        loanService.delete(loanId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{loanId}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<ReturnLoanResponseDto> returnBook(@PathVariable Long loanId, Authentication authentication){
        ReturnLoanResponseDto responseDto= loanService.returnBook(loanId, authentication);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
