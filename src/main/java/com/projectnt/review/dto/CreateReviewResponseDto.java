package com.projectnt.review.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreateReviewResponseDto {

    private long reviewId;

    public long bookId;

    public long userId;

    private int rating;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    public CreateReviewResponseDto(long reviewId, long bookId, long userId, int rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
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

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
