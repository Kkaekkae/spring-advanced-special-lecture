package com.sparta.spring.domain.entity.board;

import com.sparta.spring.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    public Board() {}

    public static Board create(User user, String title) {
        return Board.builder()
                .title(title)
                .writer(user)
                .build();
    }
}
