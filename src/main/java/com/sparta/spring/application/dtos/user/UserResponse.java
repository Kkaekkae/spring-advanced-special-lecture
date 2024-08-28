package com.sparta.spring.application.dtos.user;

import com.sparta.spring.domain.entity.user.User;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserResponse {
    Integer userId;
    String username;

    public static UserResponse of(final User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static UserResponse of(final UserDetails user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
