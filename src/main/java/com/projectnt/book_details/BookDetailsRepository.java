package com.projectnt.book_details;

import com.projectnt.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsEntity,Long> {
    Optional<BookDetailsEntity> findByBookId(long bookId);
}
