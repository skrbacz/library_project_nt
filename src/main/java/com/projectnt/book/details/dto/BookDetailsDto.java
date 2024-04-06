package com.projectnt.book.details.dto;

public class BookDetailsDto {
    private String genre;
    private String summary;
    private String coverImageUrl;

    public BookDetailsDto(String coverImageUrl, String genre, String summary) {
        this.coverImageUrl = coverImageUrl;
        this.genre = genre;
        this.summary = summary;
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
