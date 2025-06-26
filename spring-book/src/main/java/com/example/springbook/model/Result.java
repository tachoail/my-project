package com.example.springbook.model;

import com.example.springbook.enums.ResultStatus;
import lombok.Data;

@Data
public class Result<T> {
    private ResultStatus code;
    private T data;
    private String errorMessage;

    // 业务成功处理
    public static <T> Result success(T data) {
        Result result = new Result();
        result.code = ResultStatus.SUCCESS;
        result.data = data;
        result.errorMessage = "";
        return result;
    }

    // 用户未登录
    public static <T> Result noLogin() {
        Result result = new Result();
        result.code = ResultStatus.NOLOGIN;
        result.errorMessage = "用户未登录!";
        return result;
    }

    // 业务处理失败，返回错误信息
    public static <T> Result fail(String errorMessage) {
        Result result = new Result();
        result.code = ResultStatus.FAIL;
        result.errorMessage = errorMessage;
        return result;
    }

    // 业务处理失败，返回错误信息和数据
    public static <T> Result fail(String errorMessage, T data) {
        Result result = new Result();
        result.code = ResultStatus.FAIL;
        result.data = data;
        result.errorMessage = errorMessage;
        return result;
    }
}
