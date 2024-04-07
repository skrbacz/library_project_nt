package com.projectnt.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateReviewDto {

    @NotNull(message = "Book id is required")
    @Schema(name= "bookId", example = "1")
    public Long bookId;

    @NotNull(message = "User id is required")
    @Schema(name="userId", example = "1")
    public Long userId;

    @NotNull(message = "Rating is required")
    @Schema(name="rating", example = "5 (in the scale from 0 to 5)")
    private Integer rating;

    @NotBlank(message = "Comment is required")
    @Schema(name="comment", example = "comment")
    private String comment;

    @Schema(name = "reviewDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date reviewDate;

    public CreateReviewDto(Long bookId, Long userId, Integer rating, String comment, Date reviewDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
    public CreateReviewDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
