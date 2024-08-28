package com.sparta.spring.controller;

import com.sparta.spring.application.UserService;
import com.sparta.spring.application.auth.AuthUser;
import com.sparta.spring.application.dtos.auth.SignupRequestDto;
import com.sparta.spring.application.dtos.user.UserResponse;
import com.sparta.spring.config.auth.additional.UseAuthUser;
import com.sparta.spring.domain.entity.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SpringSecurityController {
    private final UserService userService;

    public SpringSecurityController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(final @RequestBody SignupRequestDto request) {

        User user = userService.signUp(request);
        return ResponseEntity.ok(user.getUsername());
    }

    @GetMapping("user-check")
    public ResponseEntity<UserResponse> authCheck(final @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @GetMapping("user-check-additional")
    public ResponseEntity<AuthUser> additionalAuthCheck(final @UseAuthUser AuthUser user) {
        return ResponseEntity.ok(user);
    }
}
