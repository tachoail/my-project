package com.example.springbook.controller;

import com.example.springbook.constant.Constants;
import com.example.springbook.model.Result;
import com.example.springbook.model.UserInfo;
import com.example.springbook.service.UserService;
import com.example.springbook.utils.JwtUtils;
import com.example.springbook.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public Result<String> login(String userName, String password) {
        log.info("用户登录,获取参数userName:{}, password:{}", userName, password);
        // 参数校验
        if(!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return Result.fail("用户名或密码为空!");
        }
        // 根据用户名进行查询
        UserInfo userInfo = userService.selectByName(userName);
        if(userInfo == null) {
            return Result.fail("用户名或密码错误!");
        }
        if(!SecurityUtils.verity(password, userInfo.getPassword())) {
            return Result.fail("密码错误!");
        }

        // 密码正确，返回token
        Map<String, Object> claim = new HashMap<>();
        claim.put(Constants.TOKEN_ID, userInfo.getId());
        claim.put(Constants.TOKEN_USERNAME, userName);
        String token = JwtUtils.genJwtToken(claim);
        log.info("UserController 返回token:{}", token);
        return Result.success(token);
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public Result<String> register( String userName, String password) {
        log.info("用户注册,获取参数userName:{}, password:{}", userName, password);
        // 参数校验
        if(!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return Result.fail("用户名或密码为空!");
        }
        // 添加用户信息
        try {
            String secretPassword = SecurityUtils.encipher(password);
            int result = userService.insertUser(userName, secretPassword);
            if(result > 0) {
                return Result.success("");
            }
        } catch (Exception e) {
            log.error("添加失败, e", e);
        }
        return Result.fail("用户名已存在!");
    }
}
