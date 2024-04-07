package com.projectnt.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectnt.book.dto.BookDto;

import java.time.LocalDate;

public class UpdateReviewResponseDto {

    private long reviewId;

    private BookDto bookId;

    private Integer rating;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    public UpdateReviewResponseDto(long reviewId, BookDto bookId, Integer rating, String comment, LocalDate reviewDate) {
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

    public BookDto getBookId() {
        return bookId;
    }

    public void setBookId(BookDto bookId) {
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

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
