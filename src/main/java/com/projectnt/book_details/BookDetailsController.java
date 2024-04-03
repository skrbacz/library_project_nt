package com.projectnt.book_details;

import com.projectnt.book_details.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book_details")
@PreAuthorize("hasRole('ADMIN')")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService= bookDetailsService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBooksDetailsPagesDto> getAllBooksDetails(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetBooksDetailsPagesDto dto=  bookDetailsService.getAll(page,size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping("/{book_id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBookDetailsDto> getOne(@PathVariable long book_id){
        GetBookDetailsDto dto = bookDetailsService.getOneByIdBD(book_id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{book_id}")
    public ResponseEntity<PatchBookDetailsResponseDto> update(@PathVariable long book_id,@RequestBody PatchBookDetailsDto dto){
        PatchBookDetailsResponseDto responseDto= bookDetailsService.update(book_id,dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

//    TODO: IT SAYS MISSING BODY BUT DEBUGGER SHOWS THAT IT WORKS
    @PostMapping
    public ResponseEntity<CreateBookDetailsResponseDto> create(@RequestBody @Validated CreateBookDetailsDto bookDetailsDto){
        var newBookDetails= bookDetailsService.create(bookDetailsDto);
        return new ResponseEntity<>(newBookDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> delete(@PathVariable long book_id){
        bookDetailsService.delete(book_id);
        return ResponseEntity.noContent().build();
    }
}
