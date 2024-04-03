package com.projectnt.book;

import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.GetBookDto;
import com.projectnt.book.dto.GetBooksPageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBooksPageResponseDto> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetBooksPageResponseDto dto = bookService.getAll(page,size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{book_id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBookDto> getOne(@PathVariable long book_id){
        GetBookDto dto = bookService.getOneById(book_id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody @Validated CreateBookDto book){
        var newBook= bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> delete(@PathVariable long book_id){
        bookService.delete(book_id);
        return ResponseEntity.accepted().build();
    }
}