package com.projectnt.loan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class CreateLoanResponseDto {

    private long loanId;

    public long bookId;

    public long userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public CreateLoanResponseDto(long loanId, long bookId, long userId, LocalDate loanDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
