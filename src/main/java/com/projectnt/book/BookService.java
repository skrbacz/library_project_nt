package com.projectnt.book;

import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.BookDto;
import com.projectnt.book.dto.BooksPageResponseDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }


    public BooksPageResponseDto getAll(int page, int size){
        Page<BookEntity> booksPage;

        Pageable pageable= PageRequest.of(page,size);

        booksPage = bookRepository.findAll(pageable);

        List<BookDto> booksDto = booksPage.getContent().stream().map(this::mapBook).toList();

        return new BooksPageResponseDto(booksDto, booksPage.getNumber(),booksPage.getTotalElements(),booksPage.getTotalPages(),booksPage.hasNext());
    }

    public BookDto getOneById(long bookId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> BookDoesntExist.createWIthId(bookId));
        return mapBook(book);
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

    @Transactional
    public UpdateBookDetailsResponseDto updateDetails(long bookId, UpdateBookDetailsDto dto){
        var book= bookRepository.findById(bookId).orElseThrow(() -> BookDoesntExist.createWIthId(bookId));

        var details= new BookEntity.BookDetailsEntity();
        details.setGenre(dto.getGenre());
        details.setSummary(dto.getSummary());
        details.setCoverImageUrl(dto.getCoverImageUrl());
        book.setBookDetails(details);

        bookRepository.save(book);

        return new UpdateBookDetailsResponseDto(book.getBookId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYearPublished(), book.getAvailableBooks(), new BookDetailsDto(details.getGenre(),details.getSummary(),details.getCoverImageUrl()));
    }

    public void delete(long bookId){
        if (!bookRepository.existsById(bookId)){
            throw BookDoesntExist.createWIthId(bookId);
        }
        bookRepository.deleteById(bookId);
    }

    private BookDto mapBook(BookEntity book) {
        BookEntity.BookDetailsEntity bookDetails = book.getBookDetails();

        if (bookDetails == null || bookDetails.getGenre() == null || bookDetails.getGenre().isEmpty()) {
            return new BookDto(
                    book.getBookId(),
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getYearPublished(),
                    book.getAvailableBooks() > 0,
                    new BookDetailsDto("", "", "")
            );
        } else {
            return new BookDto(
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
    }


}


