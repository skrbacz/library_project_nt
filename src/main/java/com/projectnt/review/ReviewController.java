package com.projectnt.review;

import com.projectnt.review.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@PreAuthorize("isAuthenticated()")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @GetMapping
    public ResponseEntity<GetReviewPagesDto> getAllReviews(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetReviewPagesDto dto = reviewService.getAll(page,size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{review_id}")
    public ResponseEntity<GetReviewDto> getOne(@PathVariable long review_id){
        GetReviewDto dto= reviewService.getOneById(review_id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{review_id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PatchReviewResponseDto> update(@PathVariable long review_id, @RequestBody PatchReviewDto dto){
        PatchReviewResponseDto responseDto= reviewService.update(review_id, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody @Validated CreateReviewDto review){
        var newReview= reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }


    @DeleteMapping("/{review_id}")
    public ResponseEntity<Void> delete(@PathVariable long review_id){
        reviewService.delete(review_id);
        return ResponseEntity.noContent().build();
    }
}
