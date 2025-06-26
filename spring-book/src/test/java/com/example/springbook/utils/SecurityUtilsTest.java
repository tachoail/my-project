package com.example.springbook.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {
    @Test
    void encipher() {
        System.out.println(SecurityUtils.encipher("admin"));
        System.out.println(SecurityUtils.encipher("zhangsan"));
        System.out.println(SecurityUtils.encipher("123456"));
        System.out.println(SecurityUtils.encipher("wangwu"));
    }
}