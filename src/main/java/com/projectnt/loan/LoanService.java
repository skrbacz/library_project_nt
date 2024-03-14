package com.projectnt.loan;

import com.projectnt.loan.dto.CreateLoanDto;
import com.projectnt.loan.dto.CreateLoanResponseDto;
import com.projectnt.loan.dto.GetLoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository){this.loanRepository= loanRepository;}

    public List<GetLoanDto> getAll(){
        var loans= loanRepository.findAll();
        return loans.stream().map((loan)-> new GetLoanDto(loan.getLoanId(),loan.getBookId(),loan.getUserId(),loan.getLoanDate(),loan.getDueDate(),loan.getReturnDate())).collect(Collectors.toList());

    }

    public GetLoanDto getOne(long loan_id){
        var loan= loanRepository.findById(loan_id).orElseThrow(()-> new RuntimeException("Loan not found"));
        return new GetLoanDto(loan.getLoanId(),loan.getBookId(),loan.getUserId(),loan.getLoanDate(),loan.getDueDate(),loan.getReturnDate());
    }

    public CreateLoanResponseDto create(CreateLoanDto loan){
        var loanEntity= new LoanEntity();
        loanEntity.setBookId(loan.getBookId());
        loanEntity.setUserId(loan.getUserId());
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);

        return new CreateLoanResponseDto(newLoan.getLoanId(),newLoan.getBookId(),newLoan.getUserId(),newLoan.getLoanDate(),newLoan.getDueDate(),newLoan.getReturnDate());
    }

    public void delete(long loan_id){
        if(!loanRepository.existsById(loan_id)){
            throw new RuntimeException();
        }
        loanRepository.deleteById(loan_id);
    }
}
