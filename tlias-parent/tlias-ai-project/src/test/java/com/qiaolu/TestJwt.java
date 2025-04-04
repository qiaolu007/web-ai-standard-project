package com.qiaolu;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJwt {
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("name", "qiaolu");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0")
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }


}
