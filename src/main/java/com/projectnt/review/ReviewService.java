package com.projectnt.review;

import com.projectnt.book.BookEntity;
import com.projectnt.book.BookRepository;
import com.projectnt.book.dto.GetBookDto;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.book.details.dto.BookDetailsDto;
import com.projectnt.review.dto.*;
import com.projectnt.review.error.ReviewDoesntExist;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import com.projectnt.user.dto.GetUserDto;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService extends OwnershipService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public GetReviewPagesDto getAll(int page, int size){
        Page<ReviewEntity> reviewPage;
        Pageable pageable= PageRequest.of(page,size);

        reviewPage =reviewRepository.findAll(pageable);
        List<GetReviewDto> reviewDto= reviewPage.getContent().stream().map(this::mapReview).toList();
        return new GetReviewPagesDto(reviewDto,reviewPage.getNumber(),reviewPage.getTotalElements(),reviewPage.getTotalPages(),reviewPage.hasNext());
    }

    public GetReviewDto getOneById(long review_id){
        var review= reviewRepository.findById(review_id).orElseThrow(()->new RuntimeException("Review not found"));
        return mapReview(review);
    }

    public GetReviewDto mapReview(ReviewEntity review){
        GetUserDto user= new GetUserDto(review.getUser().getUserId(),review.getUser().getLastName(),review.getUser().getEmail(),review.getUser().getName());
        BookDetailsDto bookDetails = new BookDetailsDto(
                review.getBook().getBookDetails().getGenre(),
                review.getBook().getBookDetails().getSummary(),
                review.getBook().getBookDetails().getCoverImageUrl()
        );

        GetBookDto book = new GetBookDto(
                review.getBook().getBookId(),
                review.getBook().getIsbn(),
                review.getBook().getTitle(),
                review.getBook().getAuthor(),
                review.getBook().getPublisher(),
                review.getBook().getYearPublished(),
                review.getBook().getAvailableBooks() > 0,
                bookDetails // Add this parameter
        );

        return new GetReviewDto(review.getReviewId(),book,user,review.getRating(),review.getComment(),review.getReviewDate());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public CreateReviewResponseDto create(CreateReviewDto reviewDto){

       UserEntity user= userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> UserDoesntExist.createWithId(reviewDto.getUserId()));
       BookEntity book= bookRepository.findById(reviewDto.getBookId()).orElseThrow(()-> BookDoesntExist.createWIthId(reviewDto.getBookId()));
       var reviewEntity= new ReviewEntity();

       reviewEntity.setBook(book);
       reviewEntity.setUser(user);
       reviewEntity.setRating(reviewDto.getRating());
       reviewEntity.setComment(reviewDto.getComment());
       reviewEntity.setReviewDate(new Date(System.currentTimeMillis()));

       var newReview = reviewRepository.save(reviewEntity);
       return new CreateReviewResponseDto(newReview.getReviewId(), newReview.getBook().getBookId(), newReview.getUser().getUserId(),newReview.getRating(),newReview.getComment(),newReview.getReviewDate());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public void delete(long review_id){
        if(!reviewRepository.existsById(review_id)){
            throw ReviewDoesntExist.createWithId(review_id);
        }
        reviewRepository.deleteById(review_id);
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#userId)")
    public PatchReviewResponseDto update(long reviewId,PatchReviewDto dto){
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow(()-> ReviewDoesntExist.createWithId(reviewId));
        BookDetailsDto bookDetails = new BookDetailsDto(
                review.getBook().getBookDetails().getGenre(),
                review.getBook().getBookDetails().getSummary(),
                review.getBook().getBookDetails().getCoverImageUrl()
        );
        var book = review.getBook();
        GetBookDto bookDto= new GetBookDto(book.getBookId(),book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getYearPublished(),book.getAvailableBooks()>0, bookDetails);

        dto.getComment().ifPresent(review::setComment);
        dto.getRating().ifPresent(review::setRating);

        reviewRepository.save(review);

        return new PatchReviewResponseDto(review.getReviewId(),bookDto,review.getRating(),review.getComment(),review.getReviewDate());
    }

}
