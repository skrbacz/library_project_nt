package com.projectnt.book_details.dto;

import com.projectnt.book.BookEntity;
import jakarta.persistence.*;

public class CreateBookDetailsResponseDto {
    private long bookId;
    private String genre;
    private String summary;
    private String coverImageUrl;

    public CreateBookDetailsResponseDto(long bookId, String genre, String summary, String coverImageUrl) {
        this.bookId = bookId;
        this.genre = genre;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
    }

    public CreateBookDetailsResponseDto() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
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
