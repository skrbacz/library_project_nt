package com.projectnt.book_details;

import com.projectnt.book.BookEntity;
import jakarta.persistence.*;

@Entity
@Table(name="booksDetails",schema = "library")
public class BookDetailsEntity {

    @Id
    @Column(name="bookId")
    private long bookId;

    @OneToOne
    @MapsId
    @JoinColumn(name="bookId")
    private BookEntity book;

    @Basic
    @Column(name="genre")
    private String genre;

    @Basic
    @Column(name="summary")
    private String summary;

    @Basic
    @Column(name= "coverImageUrl")
    private String coverImageUrl;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
