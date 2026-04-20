package com.MSanchez.SistemaHoteleriaServidor.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());

    public String generateToken(String email, String role, int id) {
        String jti = UUID.randomUUID().toString();

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("id", id)
                .setId(jti)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_400))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token);
    }
}
