package com.project_back_end.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Сервис для генерации и валидации JWT токенов.
 */
@Service
public class TokenService {

    private final String SECRET_KEY = "your_secret_key"; // ❗ Замени на безопасный ключ в .env

    // Генерация токена
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Примитивная валидация (заглушка)
    public boolean validateToken(String token) {
        return token != null && token.length() > 10;
    }
}
