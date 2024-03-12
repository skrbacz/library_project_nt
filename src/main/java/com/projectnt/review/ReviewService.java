package com.projectnt.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewEntity> getAll(){return reviewRepository.findAll();}

    public ReviewEntity getOne(long review_id){
        return reviewRepository.findById(review_id).orElseThrow(()->new RuntimeException("Review not found"));
    }

    public ReviewEntity create(ReviewEntity review){
        return reviewRepository.save(review);
    }

    public void delete(long review_id){
        if(!reviewRepository.existsById(review_id)){
            throw new RuntimeException();
        }
        reviewRepository.deleteById(review_id);
    }
}
