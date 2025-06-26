package com.example.springbook.config;

import com.example.springbook.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        log.error("发生异常e:", e);
        return Result.fail("内部错误");
    }
}
