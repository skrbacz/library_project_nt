package com.projectnt.book;

import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.GetBookDto;
import com.projectnt.book.dto.GetBooksPageResponseDto;
import com.projectnt.book.error_or_message.BookAlreadyExists;
import com.projectnt.book.error_or_message.BookDoesntExist;
import com.projectnt.book.details.dto.BookDetailsDto;
import com.projectnt.book.details.dto.UpdateBookDetailsDto;
import com.projectnt.book.details.dto.UpdateBookDetailsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }
    public GetBooksPageResponseDto getAll(int page, int size){
        Page<BookEntity> booksPage;

        Pageable pageable= PageRequest.of(page,size);

        booksPage = bookRepository.findAll(pageable);

        List<GetBookDto> booksDto = booksPage.getContent().stream().map(this::mapBook).toList();

        return new GetBooksPageResponseDto(booksDto, booksPage.getNumber(),booksPage.getTotalElements(),booksPage.getTotalPages(),booksPage.hasNext());
    }

    public GetBookDto getOneById(long book_id) {
        var book = bookRepository.findById(book_id).orElseThrow(() -> BookDoesntExist.createWIthId(book_id));
        return mapBook(book);
    }

    private GetBookDto mapBook(BookEntity book) {
        BookEntity.BookDetailsEntity bookDetails = book.getBookDetails();
        return new GetBookDto(
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
                )
        );
    }

    public CreateBookResponseDto create(CreateBookDto book){
        Optional<BookEntity> existingBook= bookRepository.findByIsbn(book.getIsbn());
        if(existingBook.isPresent()){
            throw BookAlreadyExists.createWithIsbn(book.getIsbn());
        }

        var bookEntity= new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableBooks(book.getAvailableCopies());
        bookEntity.setYearPublished(book.getYearPublished());
        var newBook= bookRepository.save(bookEntity);

        return new CreateBookResponseDto(newBook.getBookId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getYearPublished(), newBook.getAvailableBooks());
    }

    public UpdateBookDetailsResponseDto updateDetails(long book_id, UpdateBookDetailsDto dto){
        var book= bookRepository.findById(book_id).orElseThrow(() -> BookDoesntExist.createWIthId(book_id));

        var bookDetails= new BookEntity.BookDetailsEntity();
        bookDetails.setGenre(dto.getGenre());
        bookDetails.setSummary(dto.getSummary());
        bookDetails.setCoverImageUrl(dto.getCoverImageUrl());

        return new UpdateBookDetailsResponseDto(book.getBookId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYearPublished(), book.getAvailableBooks(), new BookDetailsDto(bookDetails.getGenre(), bookDetails.getSummary(), bookDetails.getCoverImageUrl()));
    }
    

    public void delete(long book_id){
        if (!bookRepository.existsById(book_id)){
            throw BookDoesntExist.createWIthId(book_id);
        }
        bookRepository.deleteById(book_id);
    }

}


