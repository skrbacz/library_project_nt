package com.projectnt.book.details.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateBookDetailsDto {

    @NotBlank(message = "Genre is required")
    private String genre;
    @NotBlank(message = "Summary is required")
    private String summary;
    @NotBlank(message = "Cover image is required")
    private String coverImageUrl;

    public UpdateBookDetailsDto(String genre, String summary, String coverImageUrl) {
        this.genre = genre;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
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

