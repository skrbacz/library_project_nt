package com.projectnt.loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Page<LoanEntity> findByUserUserId(long userId, Pageable pageable);
}
