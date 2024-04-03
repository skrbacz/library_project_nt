package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.book.BookRepository;
import com.projectnt.book.dto.GetBookDto;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.loan.dto.*;
import com.projectnt.loan.error.LoanDoesntExist;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import com.projectnt.user.dto.GetUserDto;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, AuthRepository authRepository){
        super(authRepository);
        this.loanRepository= loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    //TODO: MAKE IT SAME FOR ALL BOOKS, REVIEWS, BOOK DETAILS, USERS
    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public GetLoansPageResponseDto getAll(Long userId,int page, int size){
        Page<LoanEntity> loansPage;

        Pageable pageable= PageRequest.of(page,size);

        if(userId==null){
            loansPage= loanRepository.findAll(pageable);
        }else{
            loansPage = loanRepository.findByUserUserId(userId,pageable);
        }

        List<GetLoanDto> loansDto =loansPage.getContent().stream().map(this::mapLoan).toList();

        return new GetLoansPageResponseDto(loansDto, loansPage.getNumber(),loansPage.getTotalElements(),loansPage.getTotalPages(),loansPage.hasNext());
    }

    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,returnObject.user.userId)")
    public GetLoanDto getOneById(long loan_id){
        LoanEntity loan= loanRepository.findById(loan_id).orElseThrow(()-> LoanDoesntExist.createWithId(loan_id));
        return mapLoan(loan);
    }

    private GetLoanDto mapLoan(LoanEntity loan){
        GetUserDto user= new GetUserDto(loan.getUser().getUserId(),loan.getUser().getLastName(),loan.getUser().getEmail(),loan.getUser().getName());
        GetBookDto book= new GetBookDto(loan.getBook().getBookId(),loan.getBook().getIsbn(),loan.getBook().getTitle(),loan.getBook().getAuthor(),loan.getBook().getPublisher(),loan.getBook().getYearPublished(),loan.getBook().getAvailableBooks()>0);
        return new GetLoanDto(loan.getLoanId(),book,user,loan.getLoanDate(),loan.getDueDate());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public CreateLoanResponseDto create(CreateLoanDto loanDto){

        UserEntity user= userRepository.findById(loanDto.getUserId()).orElseThrow(() -> UserDoesntExist.createWithId(loanDto.getUserId()));
        BookEntity book= bookRepository.findById(loanDto.getBookId()).orElseThrow(()-> BookDoesntExist.createWIthId(loanDto.getBookId()));
        var loanEntity= new LoanEntity();
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setLoanDate(new Date(System.currentTimeMillis()));
        loanEntity.setDueDate(loanDto.getDueDate());

        var newLoan = loanRepository.save(loanEntity);

        return new CreateLoanResponseDto(newLoan.getLoanId(),newLoan.getBook().getBookId(),newLoan.getUser().getUserId(),newLoan.getLoanDate(),newLoan.getDueDate());
    }

    public void delete(long loan_id){
        if(!loanRepository.existsById(loan_id)){
            throw LoanDoesntExist.createWithId(loan_id);
        }
        loanRepository.deleteById(loan_id);
    }

    public PatchLoanResponseDto update(long loanId, PatchLoanDto dto) {
        LoanEntity loan= loanRepository.findById(loanId).orElseThrow(()->LoanDoesntExist.createWithId(loanId));

        dto.getReturnDate().ifPresent(loan::setReturnDate);

        loanRepository.save(loan);
        return new PatchLoanResponseDto(loan.getLoanId(),loan.getBook().getBookId(),loan.getUser().getUserId(),loan.getLoanDate(),loan.getDueDate(),loan.getReturnDate());
    }
}
