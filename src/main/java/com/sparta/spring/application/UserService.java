package com.sparta.spring.application;

import com.sparta.spring.application.dtos.auth.SignupRequestDto;
import com.sparta.spring.application.dtos.user.UserResponse;
import com.sparta.spring.domain.entity.user.User;
import com.sparta.spring.domain.enums.UserRole;
import com.sparta.spring.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signUp(SignupRequestDto request) {
        String password = passwordEncoder.encode(request.getPassword());
        User user = User.create(request.getUsername(), password, UserRole.USER);
        return userRepository.save(user);
    }

    // Pageable 파라미터를 받아 페이징을 구성하는 방법
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> user = userRepository.findByRole(UserRole.USER, pageable);
        List<UserResponse> contents = user.getContent().stream().map(UserResponse::of).toList();

        return new PageImpl<>(contents, pageable, user.getSize());
    }

    // pageNumber, pageSize 를 페이징을 구성하는 방법
    public Page<UserResponse> getUsers(Integer pageNumber, Integer pageSize) {
        List<User> users = userRepository.findAll();
        long count = userRepository.count();
        List<UserResponse> contents = users.stream().map(UserResponse::of).toList();

        return new PageImpl<>(contents, PageRequest.of(pageNumber, pageSize), count);
    }
}
