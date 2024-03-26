package com.projectnt.review;

import com.projectnt.book_details.BookDetailsService;
import com.projectnt.review.dto.CreateReviewDto;
import com.projectnt.review.dto.CreateReviewResponseDto;
import com.projectnt.review.dto.GetReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@PreAuthorize("hasRole('ADMIN')or hasRole('READER')")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @GetMapping
    public List<GetReviewDto> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping("/{review_id}")
    public GetReviewDto getOne(@PathVariable long review_id){
        return reviewService.getOne(review_id);
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review){
        var newReview= reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<Void> delete(@PathVariable long review_id){
        reviewService.delete(review_id);
        return ResponseEntity.noContent().build();
    }
}
