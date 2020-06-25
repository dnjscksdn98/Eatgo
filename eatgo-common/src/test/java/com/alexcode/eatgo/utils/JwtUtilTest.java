package com.alexcode.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;


class JwtUtilTest {

    private static String SECRET = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {
        String token = jwtUtil.createToken(1004L, "tester", null);

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = jwtUtil.createToken(1004L, "tester", 1L);
        Claims claims = jwtUtil.getClaims(token);

        assertEquals(claims.get("name"), "tester");
        assertEquals(claims.get("userId", Long.class), 1004L);
        assertEquals(claims.get("restaurantId", Long.class), 1L);
    }
}