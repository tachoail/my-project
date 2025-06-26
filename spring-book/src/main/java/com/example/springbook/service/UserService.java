package com.example.springbook.service;

import com.example.springbook.mapper.UserInfoMapper;
import com.example.springbook.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public int insertUser(String userName, String password) {
        return userInfoMapper.insertUser(userName, password);
    }

    public UserInfo selectByName(String userName) {
        return userInfoMapper.selectByName(userName);
    }
}
