package com.projectnt.book;

import com.projectnt.security.auth.AuthEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String ISBN);


}
