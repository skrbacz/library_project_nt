package com.projectnt.review;

import com.projectnt.review.dto.CreateReviewDto;
import com.projectnt.review.dto.CreateReviewResponseDto;
import com.projectnt.review.dto.GetReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<GetReviewDto> getAll(){
        var reviews= reviewRepository.findAll();
        return reviews.stream().map((review)-> new GetReviewDto(review.getReviewId(), review.getBookId(),review.getUserId(),review.getRating(),review.getCommment(),review.getReviewDate())).collect(Collectors.toList());
    }

    public GetReviewDto getOne(long review_id){
        var review= reviewRepository.findById(review_id).orElseThrow(()->new RuntimeException("Review not found"));
        return new GetReviewDto(review.getReviewId(), review.getBookId(),review.getUserId(),review.getRating(),review.getCommment(),review.getReviewDate());
    }

    public CreateReviewResponseDto create(CreateReviewDto review){
       var reviewEntity= new ReviewEntity();

       reviewEntity.setBookId(review.getBookId());
       reviewEntity.setUserId(review.getUserId());
       reviewEntity.setRating(review.getRating());
       reviewEntity.setCommment(review.getComment());

       var newReview = reviewRepository.save(reviewEntity);
       return new CreateReviewResponseDto(newReview.getReviewId(), newReview.getBookId(), newReview.getUserId(),newReview.getRating(),newReview.getCommment(),newReview.getReviewDate());
    }

    public void delete(long review_id){
        if(!reviewRepository.existsById(review_id)){
            throw new RuntimeException();
        }
        reviewRepository.deleteById(review_id);
    }
}
