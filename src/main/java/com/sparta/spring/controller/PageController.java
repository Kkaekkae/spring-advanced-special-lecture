package com.sparta.spring.controller;

import com.sparta.spring.application.UserService;
import com.sparta.spring.application.dtos.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {
    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> getUsers(PageRequest pageable) {
        Page<UserResponse> response = userService.getUsers(pageable);
        return ResponseEntity.ok(response);
    }
}
