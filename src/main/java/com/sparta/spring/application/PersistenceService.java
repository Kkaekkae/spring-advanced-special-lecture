package com.sparta.spring.application;

import com.sparta.spring.domain.entity.board.Board;
import com.sparta.spring.domain.entity.board.Board2;
import com.sparta.spring.domain.entity.user.User;
import com.sparta.spring.domain.enums.UserRole;
import com.sparta.spring.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistenceService {
    private final UserRepository userRepository;

    public PersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void testPersistence() {
        userRepository.findById(1)
                .ifPresent(user -> user.updateName("test_fix"));
    }

    @Transactional
    public void testPersistence2() {
        userRepository.findById(1)
                .ifPresent(user -> {
                    user.updateName("test_fix");
                });
    }

    @Transactional
    public void testPersistence3() {
        userRepository.findById(1)
                .ifPresent(user -> {
                    user.addArticle(Board.create(user, "test_board"));
                });
    }

    @Transactional
    public void testPersistence4() {
        User user = User.create("test_2", "1234", UserRole.USER);

        user.addArticle(Board.create(user, "test_board_2"));
        user.addArticle(Board2.create(user, "test_board_2"));
        userRepository.save(user);
    }
}
