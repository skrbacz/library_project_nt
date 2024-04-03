package com.projectnt.book_details.dto;

import org.openapitools.jackson.nullable.JsonNullable;

public class PatchBookDetailsDto {

    private JsonNullable<Long> bookId;
    private JsonNullable<String> genre;
    private JsonNullable<String> summary;
    private JsonNullable<String> coverImageUrl;

    public PatchBookDetailsDto(JsonNullable<Long> bookId, JsonNullable<String> genre, JsonNullable<String> summary, JsonNullable<String> coverImageUrl) {
        this.bookId = bookId;
        this.genre = genre;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
    }

    public JsonNullable<Long> getBookId() {
        return bookId;
    }

    public void setBookId(JsonNullable<Long> bookId) {
        this.bookId = bookId;
    }

    public JsonNullable<String> getGenre() {
        return genre;
    }

    public void setGenre(JsonNullable<String> genre) {
        this.genre = genre;
    }

    public JsonNullable<String> getSummary() {
        return summary;
    }

    public void setSummary(JsonNullable<String> summary) {
        this.summary = summary;
    }

    public JsonNullable<String> getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(JsonNullable<String> coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
