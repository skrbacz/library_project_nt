package com.projectnt.loan;

import com.projectnt.book.BookEntity;
import com.projectnt.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByBook(BookEntity book);
    Optional<LoanEntity> findByUser(UserEntity user);
}
