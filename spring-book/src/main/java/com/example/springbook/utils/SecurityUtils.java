package com.example.springbook.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class SecurityUtils {
    /**
     * 对用户注册密码进行加密
     * @param password password 用户注册密码
     * @return 数据库中存储信息(密文 + 盐值)
     */
    public static String encipher(String password) {
        // 生成随机盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 将 盐值 + 明文进行加密
        String secretPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // 返回 密文 + 盐值
        return secretPassword + salt;
    }

    /**
     * 验证密码是否正确
     * @param inputPassword 用户登录时输入的密码
     * @param sqlPassword 数据库中存储的密码(密文 + 盐值)
     * @return 密码是否正确
     */
    public static Boolean verity(String inputPassword, String sqlPassword) {
        // 校验用户输入的密码
        if(!StringUtils.hasLength(inputPassword)) {
            return false;
        }
        // 校验数据库中保存的密码
        if(!StringUtils.hasLength(sqlPassword) || sqlPassword.length() != 64) {
            return false;
        }
        // 解析盐值
        String salt = sqlPassword.substring(32, 64);
        // 生成哈希值(盐值 + 明文)
        String secretPassword = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes());
        // 判断密码是否正确
        return secretPassword.equals(sqlPassword.substring(0, 32));
    }
}
