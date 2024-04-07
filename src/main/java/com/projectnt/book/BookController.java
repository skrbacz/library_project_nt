package com.projectnt.book;

import com.projectnt.book.details.dto.UpdateBookDetailsDto;
import com.projectnt.book.details.dto.UpdateBookDetailsResponseDto;
import com.projectnt.book.dto.CreateBookDto;
import com.projectnt.book.dto.CreateBookResponseDto;
import com.projectnt.book.dto.BookDto;
import com.projectnt.book.dto.BooksPageResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService= bookService;
    }


    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<BooksPageResponseDto> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        BooksPageResponseDto dto = bookService.getAll(page,size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "401", description = "Book not found", content = @Content())
    })
    public ResponseEntity<BookDto> getOne(@PathVariable long bookId){
        BookDto dto = bookService.getOneById(bookId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody @Validated CreateBookDto book){
        var newBook= bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable long bookId){
        bookService.delete(bookId);
        return ResponseEntity.accepted().build();
    }

    //book details

    @PatchMapping("/details/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book details successfully updated"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<UpdateBookDetailsResponseDto> updateDetails(@PathVariable Long bookId, @RequestBody @Validated UpdateBookDetailsDto dto){
        UpdateBookDetailsResponseDto responseDto= bookService.updateDetails(bookId,dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }


}