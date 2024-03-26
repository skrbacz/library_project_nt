package com.projectnt.book;

import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.GetBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService= bookService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')or hasRole('READER')")
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{book_id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('READER')")

    public GetBookDto getOne(@PathVariable long book_id){
        return bookService.getOne(book_id);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody CreateBookDto book){
        var newBook= bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> delete(@PathVariable long book_id){
        bookService.delete(book_id);
        return ResponseEntity.noContent().build();
    }
}