package com.cognizant.jwtlearn;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret:MySecretKeyForJWT}")
    private String jwtSecret;

    @Value("${jwt.expiration:60000}")
    private long jwtExpiration;

    public String generateToken(String username) {
        LOGGER.debug("generateToken() called for username={}", username);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        LOGGER.debug("generateToken() token generated for username={}", username);
        return token;
    }

    public String getUsernameFromToken(String token) {
        LOGGER.debug("getUsernameFromToken() called");
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        LOGGER.debug("validateToken() called");
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            LOGGER.debug("validateToken() token is valid");
            return true;
        } catch (Exception e) {
            LOGGER.error("validateToken() error: {}", e.getMessage());
            return false;
        }
    }
}
