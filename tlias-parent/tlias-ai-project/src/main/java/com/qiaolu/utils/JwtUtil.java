package com.qiaolu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // 注意：推荐将密钥长度调整为至少 256 位（32字节）
    private static final String SECRET_KEY = "QIAOLU007MEITIANDOUZAIXIANGNIQIAOLU007MEITIANDOUZAIXIANGNIQIAOLU007MEITIANDOUZAIXIANGNI"; // 32字节字符串
    private static final long EXPIRATION = 12 * 3600 * 1000L; // 12小时

    // 使用新版 Keys 方法生成密钥
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * 生成JWT令牌（使用新版API）
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)  // 新版 claims 设置方式
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)  // 显式指定算法
                .compact();
    }

    /**
     * 解析JWT令牌（使用新版API）
     */
    public static Claims parseToken(String token) {
        try {
            logger.info("jwt token: {}", token);
            return Jwts.parser()
                    .verifyWith((SecretKey) KEY)  // 新版验证方法
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            logger.error("JWT解析失败 | Token: {} | 错误: {}", token, e.getMessage());
            return null;
        }
    }
}
