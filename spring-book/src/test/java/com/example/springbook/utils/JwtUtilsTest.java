package com.example.springbook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    // 生成随机密钥
    @Test
    void genKey() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretStr = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(secretStr);
    }
    
    @Test
    void genJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 1);
        claim.put("userName", "zhangsan");
        System.out.println(JwtUtils.genJwtToken(claim));
    }

    @Test
    void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlck5hbWUiOiJhZG1pbiIsImV4cCI6MTcxODY4NjIxNH0.1lEA-NPMRJ9eu1Q2jCc-92bhnRUDMaBelT8_DlrJIjg";
        String toke2 = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlck5hbWUiOiJ6aGFuZ3NhbiIsImV4cCI6MTcxODY4NjA3N30.QNR3vbzvW_wSwhAEkUYEPAlkQkkNUTNFu8NZ7pMqKFU";
        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims);
    }
}