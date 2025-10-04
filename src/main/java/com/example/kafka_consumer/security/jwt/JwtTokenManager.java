package com.example.kafka_consumer.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SecretKey secretKey;

    private final Integer expiration;

    public JwtTokenManager(
            @Value("${jwt.secret-key}") String keyString,
            @Value("${jwt.lifetime}") Integer expiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(keyString.getBytes());
        this.expiration = expiration;
    }

    public Long getUserIdFromJwtToken(String token) {
        logger.info("Getting userId from token");
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId", Long.class);
        } catch (Exception e) {
            logger.error("Token is invalid: ", e);
            return null;
        }
    }
}
