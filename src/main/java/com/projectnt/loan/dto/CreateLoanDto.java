package com.projectnt.loan.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateLoanDto {

    @NotNull(message = "Book id is required")
    public Long bookId;
    @NotNull(message = "User id is required")
    public Long userId;
   @NotNull(message = "Due date is required")
    private Date dueDate;

    public CreateLoanDto(Long bookId, Long userId, Date dueDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public CreateLoanDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
