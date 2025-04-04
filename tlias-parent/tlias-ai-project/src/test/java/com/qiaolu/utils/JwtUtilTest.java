package com.qiaolu.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    Map<String, Object> map = new HashMap<>();
    {
        map.put("username", "????");
        map.put("password", "444444");
    }


    @Test
    void generateToken() {
        String jwt = JwtUtil.generateToken(map);
        System.out.println(jwt);
    }

    @Test
    void parseToken() {
        System.out.println(JwtUtil.parseToken(JwtUtil.generateToken(map)));
    }
}