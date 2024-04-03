package com.projectnt.book_details;

import com.projectnt.book.BookEntity;
import com.projectnt.book.BookRepository;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.book_details.dto.*;
import com.projectnt.book_details.error.BookDetailsAlreadyExist;
import com.projectnt.book_details.error.BookDetailsDontExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository){
        this.bookDetailsRepository= bookDetailsRepository;
        this.bookRepository = bookRepository;
    }
    public GetBooksDetailsPagesDto getAll(int page, int size){
        Page<BookDetailsEntity> booksDetailsPage;
        Pageable pageable= PageRequest.of(page, size);

        booksDetailsPage = bookDetailsRepository.findAll(pageable);

        List<GetBookDetailsDto> booksDetailsDto= booksDetailsPage.getContent().stream().map(this::mapBookDetails).toList();
        return new GetBooksDetailsPagesDto(booksDetailsDto,booksDetailsPage.getNumber(),booksDetailsPage.getTotalElements(),booksDetailsPage.getTotalPages(),booksDetailsPage.hasNext());
    }

    public GetBookDetailsDto getOneByIdBD(long book_id){
        var bookDetails= bookDetailsRepository.findById(book_id).orElseThrow(()-> new RuntimeException("Book details not found"));
        return mapBookDetails(bookDetails);
    }

    public GetBookDetailsDto mapBookDetails(BookDetailsEntity bookDetails){
        return new GetBookDetailsDto(bookDetails.getBookId(),bookDetails.getBook(),bookDetails.getGenre(), bookDetails.getSummary(),bookDetails.getCoverImageUrl());
    }

    public CreateBookDetailsResponseDto create(CreateBookDetailsDto bookDetailsDto) {

        Optional<BookDetailsEntity> existingBookDetails= bookDetailsRepository.findByBookId(bookDetailsDto.getBookId());
        BookEntity book= bookRepository.findById(bookDetailsDto.getBookId()).orElseThrow(()-> BookDoesntExist.createWIthId(bookDetailsDto.getBookId()));

        if(existingBookDetails.isPresent()){
            throw BookDetailsAlreadyExist.create(bookDetailsDto.getBookId());
        }

        var bookDetailsEntity= new BookDetailsEntity();
        bookDetailsEntity.setBook(book);
        bookDetailsEntity.setBookId(bookDetailsDto.getBookId());
        bookDetailsEntity.setGenre(bookDetailsDto.getGenre());
        bookDetailsEntity.setSummary(bookDetailsDto.getSummary());
        bookDetailsEntity.setCoverImageUrl(bookDetailsDto.getCoverImageUrl());

        var newBookDetails= bookDetailsRepository.save(bookDetailsEntity);

        return new CreateBookDetailsResponseDto(newBookDetails.getBookId(), newBookDetails.getGenre(), newBookDetails.getSummary(), newBookDetails.getCoverImageUrl());

    }

    public void delete(long book_id){
        if(!bookDetailsRepository.existsById(book_id)){
            throw BookDetailsDontExist.createWithId(book_id);
        }
        bookDetailsRepository.deleteById(book_id);
    }

    public PatchBookDetailsResponseDto update(long book_id, PatchBookDetailsDto dto){
        BookDetailsEntity bookDetails= bookDetailsRepository.findByBookId(book_id).orElseThrow(()->BookDetailsDontExist.createWithId(book_id));

        dto.getBookId().ifPresent(bookDetails::setBookId);
        dto.getGenre().ifPresent(bookDetails::setGenre);
        dto.getCoverImageUrl().ifPresent(bookDetails::setCoverImageUrl);
        dto.getSummary().ifPresent(bookDetails::setSummary);

        bookDetailsRepository.save(bookDetails);
        return new PatchBookDetailsResponseDto(bookDetails.getBookId(),bookDetails.getGenre(),bookDetails.getSummary(),bookDetails.getCoverImageUrl());
    }
}

