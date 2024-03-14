package com.projectnt.loan.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;

import java.util.Date;

public class CreateLoanDto {

    public BookEntity bookId;
    public UserEntity userId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public CreateLoanDto(BookEntity bookId, UserEntity userId, Date loanDate, Date dueDate, Date returnDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public CreateLoanDto() {
    }

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
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
