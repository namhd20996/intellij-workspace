package com.example.assign.repo;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
class UserRepoTest {

    private final UserRepo userRepo;

    @Test
    void findUserByUsernameAndStatus() {
        // given

        // when

        // then

    }

    @Test
    void existsUserByUsername() {
    }
}