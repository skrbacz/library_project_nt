package com.projectnt.review.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;

import java.text.DateFormat;
import java.util.Date;

public class CreateReviewResponseDto {
    private long reviewId;
    public long bookId;
    public long userId;
    private int rating;
    private String comment;
    private Date reviewDate;

    public CreateReviewResponseDto(long reviewId, long bookId, long userId, int rating, String comment, Date reviewDate) {
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
