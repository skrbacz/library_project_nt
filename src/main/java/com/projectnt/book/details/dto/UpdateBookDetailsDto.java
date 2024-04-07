package com.projectnt.book.details.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UpdateBookDetailsDto {

    @NotBlank(message = "Genre is required")
    @Schema(name = "genre", example = "genre")
    private String genre;

    @NotBlank(message = "Summary is required")
    @Schema(name = "summary", example = "summary")
    private String summary;

    @NotBlank(message = "Cover image is required")
    @Schema(name = "coverImageUrl", example = "cover image url")
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

