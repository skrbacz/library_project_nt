package com.projectnt.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="reviews", schema = "library")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId",nullable = false)
    public BookEntity book;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName= "userId", nullable = false)
    public UserEntity user;

    @Basic
    @Column(name="rating")
    private int rating;

    @Basic
    @Column(name="comment")
    private String comment;

    @Basic
    @Column(name="reviewDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

