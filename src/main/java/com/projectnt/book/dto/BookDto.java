package com.projectnt.book.dto;

import com.projectnt.book.details.dto.BookDetailsDto;

public class BookDto {
    private long bookId;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private boolean isAvailable;
    private BookDetailsDto bookDetails;

    public BookDto(long bookId, String isbn, String title, String author, String publisher, int yearPublished, boolean isAvailable, BookDetailsDto bookDetails) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
        this.bookDetails = bookDetails;
    }

    public BookDto() {
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BookDetailsDto getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetailsDto bookDetails) {
        this.bookDetails = bookDetails;
    }
}
