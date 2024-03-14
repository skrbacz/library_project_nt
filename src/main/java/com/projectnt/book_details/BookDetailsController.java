package com.projectnt.book_details;

import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book_details.dto.CreateBookDetailsDto;
import com.projectnt.book_details.dto.CreateBookDetailsResponseDto;
import com.projectnt.book_details.dto.GetBookDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book_details")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService= bookDetailsService;
    }

    @GetMapping
    public List<GetBookDetailsDto> getAllBooksReviews(){
        return bookDetailsService.getAll();
    }
    @GetMapping("/{book_id}")
    public GetBookDetailsDto getOne(@PathVariable long book_id){
        return bookDetailsService.getOne(book_id);
    }

    @PostMapping
    public ResponseEntity<CreateBookDetailsResponseDto> create(@RequestBody CreateBookDetailsDto bookDetails){
        var newBookDetails= bookDetailsService.create(bookDetails);
        return new ResponseEntity<>(newBookDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> delete(@PathVariable long book_id){
        bookDetailsService.delete(book_id);
        return ResponseEntity.noContent().build();
    }
}
