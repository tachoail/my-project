package com.example.springbook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {
    // 设置令牌过期时间为1h
    private static final long JWT_EXPIRATION = 60 * 60 * 1000;
    // 密钥
    private static final String secretStr = "PNYvhIto8tbYt+RWiWHGusQeb8AO5TdCl9zRlqcJToo=";
    // 生成密钥
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretStr));
    /**
     * 生成令牌
     */
    public static String genJwtToken(Map<String, Object> claim) {
        // 生成令牌
        String token = Jwts.builder()
                .setClaims(claim) // 自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // 过期时间
                .signWith(key)
                .compact();
        return token;
    }
    /**
     * 令牌校验
     */
    public static Claims parseToken(String token) {
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        }catch (Exception e) {
            log.error("解析token失败, e", e);
            return null;
        }
        return claims;
    }
}
