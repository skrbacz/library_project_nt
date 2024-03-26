package com.projectnt.review.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.text.DateFormat;
import java.util.Date;

public class CreateReviewDto {
    @NotNull(message = "Book id is required")
    public BookEntity bookId;
    @NotNull(message = "User id is required")
    public UserEntity userId;
    @NotNull(message = "Rating is required")
    private int rating;
    @NotBlank(message = "Comment is required")
    private String comment;
    @NotNull(message = "Review date is required")
    private Date reviewDate;

    public CreateReviewDto(BookEntity bookId, UserEntity userId, int rating, String comment, Date reviewDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public CreateReviewDto() {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
