package com.projectnt.book_details.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookDetailsDto {
    @NotNull(message = "Book id is required")
    private Long bookId;
    @NotBlank(message = "Genre is required")
    private String genre;
    @NotBlank(message = "Summary is required")
    private String summary;
    @NotBlank(message = "Cover image is required")
    private String coverImageUrl;

    public CreateBookDetailsDto(Long bookId, String genre, String summary, String coverImageUrl) {
        this.bookId = bookId;
        this.genre = genre;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
    }

    public CreateBookDetailsDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

