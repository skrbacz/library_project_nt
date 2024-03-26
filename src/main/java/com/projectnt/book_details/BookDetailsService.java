package com.projectnt.book_details;

import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.GetBookDto;
import com.projectnt.book_details.dto.CreateBookDetailsDto;
import com.projectnt.book_details.dto.CreateBookDetailsResponseDto;
import com.projectnt.book_details.dto.GetBookDetailsDto;
import com.projectnt.book_details.error.BookDetailsAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository){
        this.bookDetailsRepository= bookDetailsRepository;
    }
    public List<GetBookDetailsDto> getAll(){
        var booksDetails =bookDetailsRepository.findAll();
        return booksDetails.stream().map((bookDetails)->new GetBookDetailsDto(bookDetails.getBookId(),bookDetails.getBook(),bookDetails.getGenre(), bookDetails.getSummary(),bookDetails.getCoverImageUrl())).collect(Collectors.toList());
    }

    public GetBookDetailsDto getOne(long book_id){
        var bookDetails= bookDetailsRepository.findById(book_id).orElseThrow(()-> new RuntimeException("Book details not found"));
        return new GetBookDetailsDto(bookDetails.getBookId(),bookDetails.getBook(),bookDetails.getGenre(), bookDetails.getSummary(),bookDetails.getCoverImageUrl());
    }

    public CreateBookDetailsResponseDto create(CreateBookDetailsDto bookDetails) {

        Optional<BookDetailsEntity> existingBookDetails= bookDetailsRepository.findByBook(bookDetails.getBook());

        if(existingBookDetails.isPresent()){
            throw BookDetailsAlreadyExist.create(bookDetails.getBook());
        }

        var bookDetailsEntity= new BookDetailsEntity();
        bookDetailsEntity.setBook(bookDetails.getBook());
        bookDetailsEntity.setGenre(bookDetails.getGenre());
        bookDetailsEntity.setSummary(bookDetails.getSummary());
        bookDetailsEntity.setCoverImageUrl(bookDetails.getCoverImageUrl());

        var newBookDetails= bookDetailsRepository.save(bookDetailsEntity);

        return new CreateBookDetailsResponseDto(newBookDetails.getBookId(),newBookDetails.getBook(),newBookDetails.getGenre(),newBookDetails.getSummary(),newBookDetails.getCoverImageUrl());

    }

    public void delete(long book_id){
        if(!bookDetailsRepository.existsById(book_id)){
            throw new RuntimeException();
        }
        bookDetailsRepository.deleteById(book_id);
    }
}

