package com.projectnt.security.auth;

import com.projectnt.security.auth.AuthEntity;
import com.projectnt.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUsername(String username);
}
