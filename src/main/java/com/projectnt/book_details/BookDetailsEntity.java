package com.projectnt.book_details;

import com.projectnt.book.BookEntity;
import jakarta.persistence.*;

@Entity
@Table(name="books_details",schema = "library")
public class BookDetailsEntity {

    @OneToOne
    @Id
    @JoinColumn(name="book_id")
    private BookEntity book_id;

    @Basic
    @Column(name="genre")
    private String genre;

    @Basic
    @Column(name="summary")
    private String summary;

    @Basic
    @Column(name= "cover_image_url")
    private String cover_image_url;

    public BookEntity getBook_id() {
        return book_id;
    }

    public void setBook_id(BookEntity book_id) {
        this.book_id = book_id;
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

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }
}
