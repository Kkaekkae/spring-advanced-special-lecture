package com.sparta.spring.domain.entity.user;

import com.sparta.spring.domain.entity.board.Board;
import com.sparta.spring.domain.entity.board.Board2;
import com.sparta.spring.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Board> articles = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Board2> secondArticles = new ArrayList<>();

    public User() {}

    @CreatedDate
    private LocalDateTime createdAt;

    public void updateName(String name) {
        this.username = name;
    }

    public static User create(String name, String password, UserRole role) {
        return User.builder()
                .username(name)
                .password(password)
                .role(role)
                .articles(new ArrayList<>())
                .secondArticles(new ArrayList<>())
                .build();
    }

    public void addArticle(Board board) {
        this.articles.add(board);
    }

    public void addArticle(Board2 board) {
        this.secondArticles.add(board);
    }
}
