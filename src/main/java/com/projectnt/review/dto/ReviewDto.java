package com.projectnt.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectnt.book.dto.BookDto;

import java.time.LocalDate;

public class ReviewDto {

    private long reviewId;

    public BookDto book;

    public String username;

    private int rating;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    public ReviewDto(long reviewId, BookDto book, String username, int rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.book = book;
        this.username = username;
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

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
