package com.alexcode.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private Key key;

    /**
     * 사용할 Signature 해싱 알고리즘: HMAC-SHA256
     * @param secret
     */

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Claims: 페이로드에 담기는 데이터들
     * Signature: 토큰이 위조되지 않았는지 검사
     *
     * @param userId
     * @param name
     * @return token
     */

    public String createToken(long userId, String name) {
        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        
        return token;
    }

    /**
     * 전달받은 토큰에 저장되어 있는 Claims 객체들을 반환
     *
     * @param token
     * @return claims
     */

    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
