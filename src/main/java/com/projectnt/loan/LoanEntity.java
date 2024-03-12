package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name= "loanId")
    private long loanId;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", nullable = false)
    public BookEntity bookId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public UserEntity userId;

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
