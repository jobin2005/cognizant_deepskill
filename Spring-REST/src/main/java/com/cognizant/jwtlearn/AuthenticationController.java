package com.cognizant.jwtlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        LOGGER.info("authenticate() - start");

        String authHeader = request.getHeader("Authorization");
        LOGGER.debug("authHeader={}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.warn("authenticate() - missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":");
            if (parts.length != 2) {
                LOGGER.warn("authenticate() - invalid credentials format");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String username = parts[0];
            String password = parts[1];
            LOGGER.debug("authenticate() - username={}", username);

            // Hardcoded validation - in production use AuthenticationManager
            if (!isValidCredentials(username, password)) {
                LOGGER.warn("authenticate() - invalid credentials for username={}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String token = jwtTokenProvider.generateToken(username);
            JwtResponse response = new JwtResponse(token);

            LOGGER.info("authenticate() - end");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            LOGGER.error("authenticate() - error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Hardcoded validation for demo - in production use UserDetailsService
        return "user".equals(username) && "pwd".equals(password);
    }
}
