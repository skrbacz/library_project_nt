package com.projectnt.loan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateLoanDto {

    @NotNull(message = "Book id is required")
    @Schema(name = "bookId", example = "1")
    public Long bookId;

    @NotNull(message = "User id is required")
    @Schema(name = "userId", example = "1")
    public Long userId;

    @NotNull(message = "Due date is required")
    @Schema(name = "dueDate", example = "2024-02-17")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public CreateLoanDto(Long bookId, Long userId, LocalDate dueDate) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
