package com.projectnt.loan.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateLoanDto {

    @NotNull(message = "Book is required")
    public BookEntity book;
    @NotNull(message = "User is required")
    public UserEntity user;
    @NotNull(message = "Loan date is required")
    private Date loanDate;
    @NotNull(message = "Due date is required")
    private Date dueDate;
    @NotNull(message = "Return date is required")
    private Date returnDate;

    public CreateLoanDto(BookEntity book, UserEntity user, Date loanDate, Date dueDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public CreateLoanDto() {
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
