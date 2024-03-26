package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name= "loanId")
    private long loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", nullable = false)
    public BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public UserEntity user;

    @Column(name="loanDate")
    private Date loanDate;

    @Basic
    @Column(name= "dueDate")
    private Date dueDate;

    @Basic
    @Column(name="returnDate")
    private Date returnDate;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
