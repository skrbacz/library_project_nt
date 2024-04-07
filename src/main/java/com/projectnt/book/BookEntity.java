package com.projectnt.book;

import com.projectnt.loan.LoanEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "books", schema= "library")
public class BookEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;
    @Basic
    @Column(name = "isbn", unique = true)
    private String isbn;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "publisher")
    private String publisher;
    @Basic
    @Column(name = "yearPublished")
    private int yearPublished;
    @Basic
    @Column(name = "availableBooks")
    private int availableBooks;

    @Embedded
    private BookDetailsEntity bookDetails;


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public BookEntity(String author, int availableBooks, BookDetailsEntity bookDetails, long bookId, String isbn, List<LoanEntity> loans, String publisher, String title, int yearPublished) {
        this.author = author;
        this.availableBooks = availableBooks;
        this.bookDetails = bookDetails;
        this.bookId = bookId;
        this.isbn = isbn;
        this.loans = loans;
        this.publisher = publisher;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    public BookEntity() {

    }

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public BookDetailsEntity getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetailsEntity bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Embeddable
    public static class BookDetailsEntity {
        @Basic
        @Column(name = "genre")
        private String genre;

        @Basic
        @Column(name = "summary")
        private String summary;

        @Basic
        @Column(name = "coverImageUrl")
        private String coverImageUrl;

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }
    }
}
