package com.example.springbook.config;

import com.example.springbook.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    // supports方法，用于判断是否要执行beforeBodyWrite方法，true为执行，false不执行，
    // 可以通过supports方法选择哪些类或哪些方法的response需要进行处理，哪些不需要进行处理
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 所有方法都进行处理
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 方法返回的结果已经是Result类型，直接返回Result
        if(body instanceof Result) {
            return body;
        }
        // 返回的结果是String类型，使用SpringBoot内置提供的Jackson来实现信息的序列化
        if(body instanceof String) {
            return objectMapper.writeValueAsString(Result.success(body));
        }
        // 其他情况，调用Result.success方法，返回Result类型数据
        return Result.success(body);
    }
}
