package com.projectnt.review;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {

    Page<ReviewEntity> findAllByBookBookId(long bookId, Pageable pageable);
}
