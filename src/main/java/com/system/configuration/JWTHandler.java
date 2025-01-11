package com.system.configuration;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.system.model.UserModel;

@Component
public class JWTHandler {

    private final Algorithm secretKey; // Nem static mező

    // Konstruktor-injektálás a secret key-hez
    public JWTHandler(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = Algorithm.HMAC256(secretKey); // HMAC256 algoritmus inicializálása
    }

    public String generation(UserModel user) {
        return JWT.create()
                .withSubject(user.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 óra lejárat
                .sign(secretKey); // Az algoritmus aláírásra használata
    }
}
