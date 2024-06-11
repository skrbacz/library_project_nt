package com.projectnt.review;

import com.projectnt.review.dto.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ReviewPagesDto> getAllReviews(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        ReviewPagesDto dto = reviewService.getAll(page,size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ReviewPagesDto> getAllReviewsForOneBook(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable int bookId){
        ReviewPagesDto dto = reviewService.getAllForOneBook(bookId,page,size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "401", description = "Review not found", content = @Content())
    })
    public ResponseEntity<ReviewDto> getOne(@PathVariable long reviewId){
        ReviewDto dto= reviewService.getOneById(reviewId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review successfully created"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody @Validated CreateReviewDto review){
        var newReview= reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }


    @DeleteMapping("/{reviewId}")
    @ApiResponse(responseCode = "200", description = "Review deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable long reviewId, Authentication authentication){
        reviewService.delete(reviewId, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{reviewId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update successful"),
            @ApiResponse(responseCode = "401", description = "Update failed", content = @Content())
    })
    public ResponseEntity<UpdateReviewResponseDto> update(@PathVariable Long reviewId, @RequestBody UpdateReviewDto dto, Authentication authentication){
        UpdateReviewResponseDto responseDto= reviewService.update(reviewId,dto, authentication);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
