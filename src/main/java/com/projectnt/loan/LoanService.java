package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.book.BookRepository;
import com.projectnt.book.dto.BookDto;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.book.details.dto.BookDetailsDto;
import com.projectnt.loan.dto.*;
import com.projectnt.loan.error.LoanAlreadyReturned;
import com.projectnt.loan.error.LoanDoesntExist;
import com.projectnt.security.error.UnauthorizedAttemptError;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import com.projectnt.user.dto.UserDto;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, AuthRepository authRepository) {
        super(authRepository);
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public LoansPageResponseDto getAll(Long userId, int page, int size) {
        Page<LoanEntity> loansPage;

        Pageable pageable = PageRequest.of(page, size);

        if (userId == null) {
            loansPage = loanRepository.findAll(pageable);
        } else {
            loansPage = loanRepository.findByUserUserId(userId, pageable);
        }

        List<LoanDto> loansDto = loansPage.getContent().stream().map(this::mapLoan).toList();

        return new LoansPageResponseDto(loansDto, loansPage.getNumber(), loansPage.getTotalElements(), loansPage.getTotalPages(), loansPage.hasNext());
    }

    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,returnObject.user.userId)")
    public LoanDto getOneById(long loanId) {
        LoanEntity loan = loanRepository.findById(loanId).orElseThrow(() -> LoanDoesntExist.createWithId(loanId));
        return mapLoan(loan);
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")
    public CreateLoanResponseDto create(CreateLoanDto loanDto) {

        UserEntity user = userRepository.findById(loanDto.getUserId()).orElseThrow(() -> UserDoesntExist.createWithId(loanDto.getUserId()));
        BookEntity book = bookRepository.findById(loanDto.getBookId()).orElseThrow(() -> BookDoesntExist.createWIthId(loanDto.getBookId()));

        var loanEntity = new LoanEntity();
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setLoanDate(LocalDate.now());
        loanEntity.setDueDate(loanDto.getDueDate());

        var newLoan = loanRepository.save(loanEntity);

        return new CreateLoanResponseDto(newLoan.getLoanId(), newLoan.getBook().getBookId(), newLoan.getUser().getUserId(), newLoan.getLoanDate(), newLoan.getDueDate());
    }

    public void delete(long loanId) {
        if (!loanRepository.existsById(loanId)) {
            throw LoanDoesntExist.createWithId(loanId);
        }
        loanRepository.deleteById(loanId);
    }

    public ReturnLoanResponseDto returnBook(Long loanId, Authentication authentication) {
        var loan = loanRepository.findById(loanId).orElseThrow(() -> LoanDoesntExist.createWithId(loanId));

        if(!isOwnerOrAdmin(getAuthInfo(authentication), loan.getUser().getUserId())){
            throw UnauthorizedAttemptError.create();
        }

        if(loan.getReturnDate() != null){
            throw LoanAlreadyReturned.create(loan.getLoanId());
        }

        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        return new ReturnLoanResponseDto(loan.getLoanId(), loan.getBook().getBookId(), loan.getUser().getUserId(), loan.getLoanDate(), loan.getDueDate(), loan.getReturnDate());
    }

    private LoanDto mapLoan(LoanEntity loan) {
        var auth = authRepository.findByUserUserId(loan.getUser().getUserId());
        UserDto user = new UserDto(
                loan.getUser().getUserId(),
                loan.getUser().getLastName(),
                loan.getUser().getEmail(),
                loan.getUser().getName(),
                auth.getUsername()
        );

        BookDetailsDto bookDetails = new BookDetailsDto(
                loan.getBook().getBookDetails().getGenre(),
                loan.getBook().getBookDetails().getSummary(),
                loan.getBook().getBookDetails().getCoverImageUrl()
        );

        BookDto book = new BookDto(
                loan.getBook().getBookId(),
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getBook().getAuthor(),
                loan.getBook().getPublisher(),
                loan.getBook().getYearPublished(),
                loan.getBook().getAvailableBooks() > 0,
                bookDetails // Add this parameter
        );

        return new LoanDto(
                loan.getLoanId(),
                book,
                user,
                loan.getLoanDate(),
                loan.getDueDate()
        );
    }

}
