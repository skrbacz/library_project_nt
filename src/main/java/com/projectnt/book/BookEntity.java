package com.projectnt.book;

import jakarta.persistence.*;

@Entity
@Table(name= "books", schema= "library")
public class BookEntity {

    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;
    @Basic
    @Column(name="isbn", unique = true)
    private String isbn;
    @Basic
    @Column(name="title")
    private String title;
    @Basic
    @Column(name="author")
    private String author;
    @Basic
    @Column(name="publisher")
    private String publisher;
    @Basic
    @Column(name="yearPublished")
    private int yearPublished;
    @Basic
    @Column(name="availableBooks")
    private int availableBooks;

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
}
