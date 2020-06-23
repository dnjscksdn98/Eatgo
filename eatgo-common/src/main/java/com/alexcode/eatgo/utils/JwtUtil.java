package com.alexcode.eatgo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private Key key;

    public JwtUtil(String secret) {
        // 사용할 Signature 해싱 알고리즘: HMAC-SHA256
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(long userId, String name) {
        String token = Jwts.builder()
                .claim("userId", userId)  // Claims: 페이로드에 담기는 데이터들
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)  // Signature: 토큰이 위조되지 않았는지 검사
                .compact();
        
        return token;
    }
}
