package com.projectnt.review;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.util.List;

@Entity
@Table(name="reviews", schema = "library")
public class ReviewEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "reviewId")
    private long reviewId;

    @OneToMany
    @JoinColumn(name= "userId")
    private List<UserEntity> userId;

    @OneToMany
    @JoinColumn(name= "bookId")
    private List<BookEntity> bookId;

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

    public List<UserEntity> getUserId() {
        return userId;
    }

    public void setUserId(List<UserEntity> userId) {
        this.userId = userId;
    }

    public List<BookEntity> getBookId() {
        return bookId;
    }

    public void setBookId(List<BookEntity> bookId) {
        this.bookId = bookId;
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
