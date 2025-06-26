package com.example.springbook.interceptor;

import com.example.springbook.constant.Constants;
import com.example.springbook.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor preHandle...");
        // 获取token
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        log.info("从header中获取token:{}", token);
        // 校验token, 判断是否放行
        Claims claims = JwtUtils.parseToken(token);
        if(claims == null) {
            // 校验失败
            response.setStatus(401);
            return false;
        }
        // 校验成功，放行
        return true;
    }
}
