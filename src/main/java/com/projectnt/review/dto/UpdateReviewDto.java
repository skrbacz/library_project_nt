package com.projectnt.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.openapitools.jackson.nullable.JsonNullable;

public class UpdateReviewDto {

    @Schema(name= "rating", example = "5 (in the scale from 0 to 5)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private JsonNullable<Integer> rating;

    @Schema(name= "comment", example = "comment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private JsonNullable<String> comment;

    public UpdateReviewDto(JsonNullable<Integer> rating, JsonNullable<String> comment) {
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
