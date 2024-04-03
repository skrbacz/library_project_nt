package com.projectnt.review.dto;

import org.openapitools.jackson.nullable.JsonNullable;

public class PatchReviewDto {

    private JsonNullable<Integer> rating;
    private JsonNullable<String> comment;

    public PatchReviewDto(JsonNullable<Integer> rating, JsonNullable<String> comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public JsonNullable<Integer> getRating() {
        return rating;
    }

    public void setRating(JsonNullable<Integer> rating) {
        this.rating = rating;
    }

    public JsonNullable<String> getComment() {
        return comment;
    }

    public void setComment(JsonNullable<String> comment) {
        this.comment = comment;
    }
}
