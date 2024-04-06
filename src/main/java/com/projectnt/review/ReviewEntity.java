package com.projectnt.review;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

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
    private Date reviewDate;

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity bookId) {
        this.book = bookId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userId) {
        this.user = userId;
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

