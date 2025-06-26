package com.example.springbook.mapper;

import com.example.springbook.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void selectByName() {
        userInfoMapper.selectByName("admin");
    }
    @Test
    void insertUser() {
        System.out.println(userInfoMapper.insertUser("lisi", "123456"));
    }
}