package com.projectnt.review.dto;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;

import java.text.DateFormat;

public class CreateReviewDto {
    public BookEntity bookId;
    public UserEntity userId;
    private int rating;
    private String commment;
    private DateFormat reviewDate;

    public CreateReviewDto(BookEntity bookId, UserEntity userId, int rating, String commment, DateFormat reviewDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.commment = commment;
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

    public String getCommment() {
        return commment;
    }

    public void setCommment(String commment) {
        this.commment = commment;
    }

    public DateFormat getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(DateFormat reviewDate) {
        this.reviewDate = reviewDate;
    }
}
