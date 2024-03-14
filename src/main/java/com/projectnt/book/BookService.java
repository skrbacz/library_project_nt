package com.projectnt.book;

import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.GetBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }
    public List<GetBookDto> getAll(){
        var books =bookRepository.findAll();
        return books.stream().map((book)->new GetBookDto(book.getBookId(),book.getIsbn(),book.getTitle(), book.getAuthor(), book.getPublisher(),book.getYearPublished(),book.getAvailableBooks()>0)).collect(Collectors.toList());
    }

    public GetBookDto getOne(long book_id){
        var book= bookRepository.findById(book_id).orElseThrow(()-> new RuntimeException("Book not found"));
        return new GetBookDto(book.getBookId(),book.getIsbn(),book.getTitle(), book.getAuthor(), book.getPublisher(),book.getYearPublished(),book.getAvailableBooks()>0);
    }

    public CreateBookResponseDto create(CreateBookDto book){
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

    public void delete(long book_id){
        if (!bookRepository.existsById(book_id)){
            throw new RuntimeException();
        }
        bookRepository.deleteById(book_id);
    }
}
