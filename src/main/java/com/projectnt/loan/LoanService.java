package com.projectnt.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository){this.loanRepository= loanRepository;}

    public List<LoanEntity> getAll(){return loanRepository.findAll();}

    public LoanEntity getOne(long loan_id){
        return loanRepository.findById(loan_id).orElseThrow(()-> new RuntimeException("Loan not found"));
    }

    public LoanEntity create(LoanEntity loan){
        return loanRepository.save(loan);
    }

    public void delete(long loan_id){
        if(!loanRepository.existsById(loan_id)){
            throw new RuntimeException();
        }
        loanRepository.deleteById(loan_id);
    }
}
