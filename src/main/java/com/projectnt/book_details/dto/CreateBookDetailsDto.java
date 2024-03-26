package com.projectnt.book_details.dto;

import com.projectnt.book.BookEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookDetailsDto {
    @NotNull(message = "Book is required")
    private BookEntity book;
    @NotBlank(message = "Genre is required")
    private String genre;
    @NotBlank(message = "Summary is required")
    private String summary;
    @NotBlank(message = "Cover image is required")
    private String coverImageUrl;

    public CreateBookDetailsDto(BookEntity book, String genre, String summary, String coverImageUrl) {
        this.book = book;
        this.genre = genre;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
    }

    public CreateBookDetailsDto() {
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

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

