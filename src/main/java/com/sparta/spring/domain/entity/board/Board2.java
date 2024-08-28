package com.sparta.spring.domain.entity.board;

import com.sparta.spring.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    public Board2() {}

    public static Board2 create(User user, String title) {
        return Board2.builder()
                .title(title)
                .writer(user)
                .build();
    }
}
