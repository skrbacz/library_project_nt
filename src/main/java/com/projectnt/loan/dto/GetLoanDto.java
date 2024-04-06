package com.projectnt.loan.dto;

import com.projectnt.book.dto.GetBookDto;
import com.projectnt.user.dto.GetUserDto;

import java.util.Date;

public class GetLoanDto {

    private long loanId;
    public GetBookDto book;
    public GetUserDto user;
    private Date loanDate;
    private Date dueDate;

    public GetLoanDto(long loanId, GetBookDto book, GetUserDto user, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public GetLoanDto() {
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
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
}
