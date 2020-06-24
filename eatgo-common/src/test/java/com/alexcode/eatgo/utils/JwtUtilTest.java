package com.alexcode.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;


class JwtUtilTest {

    private static String SECRET = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {
        String token = jwtUtil.createToken(1004L, "tester");

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = jwtUtil.createToken(1004L, "tester");
        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("name"), is("tester"));
        assertThat(claims.get("userId", Long.class), is(1004L));
    }
}