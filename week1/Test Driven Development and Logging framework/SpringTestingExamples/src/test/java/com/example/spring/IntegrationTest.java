package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFullFlow_createAndGetUser() {
        User u = new User(null, "IntegrationUser");
        ResponseEntity<User> resp = restTemplate.postForEntity("http://localhost:" + port + "/users", u, User.class);
        assertThat(resp.getStatusCode().is2xxSuccessful()).isTrue();
        User saved = resp.getBody();
        assertThat(saved).isNotNull();

        ResponseEntity<User> get = restTemplate.getForEntity("http://localhost:" + port + "/users/" + saved.getId(), User.class);
        assertThat(get.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(get.getBody().getName()).isEqualTo("IntegrationUser");
    }
}
