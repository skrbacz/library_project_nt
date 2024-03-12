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
    @Column(name = "review_id")
    private long review_id;

    @OneToMany
    @JoinColumn(name= "user_id")
    private List<UserEntity> user_id;

    @OneToMany
    @JoinColumn(name= "book_id")
    private List<BookEntity> book_id;

    @Basic
    @Column(name="rating")
    private int rating;

    @Basic
    @Column(name="commment")
    private String commment;

    @Basic
    @Column(name="review_date")
    private DateFormat review_date;

    public long getReview_id() {
        return review_id;
    }

    public void setReview_id(long review_id) {
        this.review_id = review_id;
    }

    public List<UserEntity> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<UserEntity> user_id) {
        this.user_id = user_id;
    }

    public List<BookEntity> getBook_id() {
        return book_id;
    }

    public void setBook_id(List<BookEntity> book_id) {
        this.book_id = book_id;
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

    public DateFormat getReview_date() {
        return review_date;
    }

    public void setReview_date(DateFormat review_date) {
        this.review_date = review_date;
    }
}
