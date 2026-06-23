package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryCustomQueryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        userRepository.save(new User(1L, "Alice"));
        userRepository.save(new User(2L, "Alice"));
        userRepository.save(new User(3L, "Bob"));

        List<User> alices = userRepository.findByName("Alice");
        assertThat(alices).hasSize(2);
    }
}
