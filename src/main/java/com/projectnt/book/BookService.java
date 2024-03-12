package com.projectnt.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }
    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }

    public BookEntity getOne(long book_id){
        return bookRepository.findById(book_id).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public BookEntity create(BookEntity book){return bookRepository.save(book);}

    public void delete(long book_id){
        if (!bookRepository.existsById(book_id)){
            throw new RuntimeException();
        }
        bookRepository.deleteById(book_id);
    }
}
