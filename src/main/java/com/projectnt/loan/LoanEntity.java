package com.projectnt.loan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name= "loanId")
    private long loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", nullable = false)
    public BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public UserEntity user;

    @Column(name="loanDate",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    @Basic
    @Column(name= "dueDate",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Basic
    @Column(name="returnDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;


    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
