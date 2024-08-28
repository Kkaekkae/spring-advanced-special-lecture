package com.sparta.spring.domain.repository;

import com.sparta.spring.domain.entity.user.User;
import com.sparta.spring.domain.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Page<User> findByRole(UserRole role, Pageable pageable);

    Page<User> findByCreatedAtAfter(LocalDateTime startDate, Pageable pageable);
}
