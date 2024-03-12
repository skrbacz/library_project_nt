package com.projectnt.review;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.awt.print.Book;
import java.text.DateFormat;
import java.util.List;

@Entity
@Table(name="reviews", schema = "library")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId",nullable = false)
    public BookEntity bookId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName= "userId", nullable = false)
    public UserEntity userId;

    @Basic
    @Column(name="rating")
    private int rating;

    @Basic
    @Column(name="commment")
    private String commment;

    @Basic
    @Column(name="reviewDate")
    private DateFormat reviewDate;

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
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

