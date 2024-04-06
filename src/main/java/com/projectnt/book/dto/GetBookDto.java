package com.projectnt.book.dto;

import com.projectnt.book.details.dto.BookDetailsDto;

public class GetBookDto {
    private long bookId;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private boolean isAvailable;
    private BookDetailsDto bookDetailsDto;

    public GetBookDto(long bookId, String isbn, String title, String author, String publisher, int yearPublished, boolean isAvailable, BookDetailsDto bookDetailsDto) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
        this.bookDetailsDto = bookDetailsDto;
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

    public BookDetailsDto getBookDetailsDto() {
        return bookDetailsDto;
    }

    public void setBookDetailsDto(BookDetailsDto bookDetailsDto) {
        this.bookDetailsDto = bookDetailsDto;
    }
}
