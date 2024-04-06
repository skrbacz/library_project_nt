package com.projectnt.review.dto;

import com.projectnt.book.dto.GetBookDto;

import java.util.Date;

public class PatchReviewResponseDto {

    private long reviewId;
    private GetBookDto bookId;
    private Integer rating;
    private String comment;
    private Date reviewDate;

    public PatchReviewResponseDto(long reviewId, GetBookDto bookId, Integer rating, String comment, Date reviewDate) {
        this.reviewId = reviewId;
        this.bookId = bookId;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
