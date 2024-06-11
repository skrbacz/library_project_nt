package com.projectnt.review;

import com.projectnt.book.BookEntity;
import com.projectnt.book.BookRepository;
import com.projectnt.book.dto.BookDto;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.book.details.dto.BookDetailsDto;
import com.projectnt.review.dto.*;
import com.projectnt.review.error.RatingError;
import com.projectnt.review.error.ReviewDoesntExist;
import com.projectnt.security.error.UnauthorizedAttemptError;
import com.projectnt.security.auth.AuthRepository;
import com.projectnt.security.auth.OwnershipService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public ReviewPagesDto getAll(int page, int size){
        Page<ReviewEntity> reviewPage;
        Pageable pageable= PageRequest.of(page,size);

        reviewPage =reviewRepository.findAll(pageable);
        List<ReviewDto> reviewDtos= reviewPage.getContent().stream().map(this::mapReview).toList();
        return new ReviewPagesDto(reviewDtos,reviewPage.getNumber(),reviewPage.getTotalElements(),reviewPage.getTotalPages(),reviewPage.hasNext());
    }

    public ReviewPagesDto getAllForOneBook(int bookId,int page, int size){
        Page<ReviewEntity> reviewPage;
        Pageable pageable= PageRequest.of(page,size, Sort.by("reviewDate").descending());

        reviewPage =reviewRepository.findAllByBookBookId(bookId,pageable);
        List<ReviewDto> reviewDtos= reviewPage.getContent().stream().map(this::mapReview).toList();
        return new ReviewPagesDto(reviewDtos,reviewPage.getNumber(),reviewPage.getTotalElements(),reviewPage.getTotalPages(),reviewPage.hasNext());
    }

    public ReviewDto getOneById(long reviewId){
        var review= reviewRepository.findById(reviewId).orElseThrow(()->ReviewDoesntExist.createWithId(reviewId));
        return mapReview(review);
    }

    public ReviewDto mapReview(ReviewEntity review){
        var username= authRepository.findByUserUserId(review.getUser().getUserId()).getUsername();

        var bookDto = getGetBookDtoFromReview(review);

        return new ReviewDto(review.getReviewId(),bookDto,username,review.getRating(),review.getComment(),review.getReviewDate());
    }


    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name,#reviewDto.userId)")
    public CreateReviewResponseDto create(CreateReviewDto reviewDto){

       UserEntity user= userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> UserDoesntExist.createWithId(reviewDto.getUserId()));
       BookEntity book= bookRepository.findById(reviewDto.getBookId()).orElseThrow(()-> BookDoesntExist.createWIthId(reviewDto.getBookId()));
       var reviewEntity= new ReviewEntity();

       reviewEntity.setBook(book);
       reviewEntity.setUser(user);
       if (checkRating(reviewDto.getRating())){
           reviewEntity.setRating(reviewDto.getRating());
       }else{
           throw RatingError.outOfBounds();
       }
       reviewEntity.setComment(reviewDto.getComment());
       reviewEntity.setReviewDate(LocalDate.now());

       var newReview = reviewRepository.save(reviewEntity);
       return new CreateReviewResponseDto(newReview.getReviewId(), newReview.getBook().getBookId(), newReview.getUser().getUserId(),newReview.getRating(),newReview.getComment(),newReview.getReviewDate());
    }

    public void delete(long reviewId, Authentication authentication){
        var review= reviewRepository.findById(reviewId).orElseThrow(()-> ReviewDoesntExist.createWithId(reviewId));

        if(!isOwnerOrAdmin(getAuthInfo(authentication), review.getUser().getUserId())){
            throw UnauthorizedAttemptError.create();
        }
        reviewRepository.deleteById(reviewId);
    }


    public UpdateReviewResponseDto update(long reviewId, UpdateReviewDto dto, Authentication authentication) {
        var review = reviewRepository.findById(reviewId).orElseThrow(() -> ReviewDoesntExist.createWithId(reviewId));

        if(!isOwnerOrAdmin(getAuthInfo(authentication), review.getUser().getUserId())){
            throw UnauthorizedAttemptError.create();
        }
        var bookDto= getGetBookDtoFromReview(review);
        updatingReview(dto,review);
        reviewRepository.save(review);
        return new UpdateReviewResponseDto(review.getReviewId(),bookDto,review.getRating(),review.getComment(),LocalDate.now());
    }


    private void updatingReview(UpdateReviewDto dto, ReviewEntity review) {
        dto.getComment().ifPresent(review::setComment);

        dto.getRating().ifPresent(rating -> {
            if (checkRating(rating)) {
                review.setRating(rating);
            } else {
                throw RatingError.outOfBounds();
            }
        });
    }

    public boolean checkRating(int rating) {
        return rating >= 0 && rating <= 5;
    }

    private static BookDto getGetBookDtoFromReview(ReviewEntity review) {
        var book = review.getBook();
        BookEntity.BookDetailsEntity bookDetails= book.getBookDetails();

        var bookDto = new BookDto();

        if (bookDetails == null || bookDetails.getGenre() == null || bookDetails.getGenre().isEmpty()) {
            bookDto=  new BookDto(
                    book.getBookId(),
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getYearPublished(),
                    book.getAvailableBooks() > 0,
                    new BookDetailsDto("", "", ""),
                    book.getAvailableBooks()
            );
        } else {
            bookDto= new BookDto(
                    book.getBookId(),
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getYearPublished(),
                    book.getAvailableBooks() > 0,
                    new BookDetailsDto(
                            bookDetails.getGenre(),
                            bookDetails.getSummary(),
                            bookDetails.getCoverImageUrl()
                    ),
                    bookDto.getAvailableBooks()
            );
        }
        return bookDto;
    }

}
