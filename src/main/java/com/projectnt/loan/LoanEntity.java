package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.util.List;

@Entity
@Table(name="loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name= "loan_id")
    private long loan_id;

    @OneToMany
    @JoinColumn(name = "book_id")
    private List<BookEntity> book;

    @OneToMany
    @JoinColumn(name= "user_id")
    private List<UserEntity> user_id;

    @Basic
    @Column(name="loan_date")
    private DateFormat loan_date;

    @Basic
    @Column(name= "due_date")
    private DateFormat due_date;

    @Basic
    @Column(name="retun_date")
    private DateFormat retun_date;

    public long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(long loan_id) {
        this.loan_id = loan_id;
    }

    public List<BookEntity> getBook() {
        return book;
    }

    public void setBook(List<BookEntity> book) {
        this.book = book;
    }

    public List<UserEntity> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<UserEntity> user_id) {
        this.user_id = user_id;
    }

    public DateFormat getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(DateFormat loan_date) {
        this.loan_date = loan_date;
    }

    public DateFormat getDue_date() {
        return due_date;
    }

    public void setDue_date(DateFormat due_date) {
        this.due_date = due_date;
    }

    public DateFormat getRetun_date() {
        return retun_date;
    }

    public void setRetun_date(DateFormat retun_date) {
        this.retun_date = retun_date;
    }
}
