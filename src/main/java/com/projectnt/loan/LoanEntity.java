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
    @Column(name= "loanId")
    private long loanId;

    @OneToMany
    @JoinColumn(name = "bookId")
    private List<BookEntity> book;

    @OneToMany
    @JoinColumn(name= "userId")
    private List<UserEntity> userId;

    @Basic
    @Column(name="loanDate")
    private DateFormat loanDate;

    @Basic
    @Column(name= "dueDate")
    private DateFormat dueDate;

    @Basic
    @Column(name="returnDate")
    private DateFormat returnDate;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public List<BookEntity> getBook() {
        return book;
    }

    public void setBook(List<BookEntity> book) {
        this.book = book;
    }

    public List<UserEntity> getUserId() {
        return userId;
    }

    public void setUserId(List<UserEntity> userId) {
        this.userId = userId;
    }

    public DateFormat getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(DateFormat loanDate) {
        this.loanDate = loanDate;
    }

    public DateFormat getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateFormat dueDate) {
        this.dueDate = dueDate;
    }

    public DateFormat getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateFormat returnDate) {
        this.returnDate = returnDate;
    }
}
