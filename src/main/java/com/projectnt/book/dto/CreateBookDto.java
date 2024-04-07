package com.projectnt.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookDto {

    @NotBlank(message = "Isbn is required")
    @Schema(name = "isbn", example = "isbn")
    private String isbn;

    @NotBlank(message = "Title is required")
    @Schema(name = "title", example = "title")
    private String title;

    @NotBlank(message = "Author is required")
    @Schema(name = "author", example = "author" )
    private String author;

    @NotBlank(message = "Publisher is required")
    @Schema(name= "publisher", example = "publisher")
    private String publisher;

    @NotNull(message = "Year of publication is required")
    @Schema(name = "yearPublished", example = "YYYY")
    private Integer yearPublished;

    @NotNull(message = "Amount of available copies is required")
    @Schema(name = "availableCopies", example = "10")
    private Integer availableCopies;


    public CreateBookDto(String isbn, String title, String author, String publisher, Integer yearPublished, Integer availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.availableCopies = availableCopies;
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

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
