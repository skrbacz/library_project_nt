package com.projectnt.review.dto;

import com.projectnt.book.dto.GetBookDto;
import com.projectnt.user.dto.GetUserDto;

import java.util.Date;

public class GetReviewDto {

    private long reviewId;
    public GetBookDto bookId;
    public GetUserDto userId;
    private int rating;
    private String comment;
    private Date reviewDate;

    public GetReviewDto(long reviewId, GetBookDto bookId, GetUserDto userId, int rating, String comment, Date reviewDate) {
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

    public GetBookDto getBookId() {
        return bookId;
    }

    public void setBookId(GetBookDto bookId) {
        this.bookId = bookId;
    }

    public GetUserDto getUserId() {
        return userId;
    }

    public void setUserId(GetUserDto userId) {
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
