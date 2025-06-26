package com.example.springbook.enums;

public enum ResultStatus {
    SUCCESS(200),
    FAIL(-1),
    NOLOGIN(-2)
    ;
    private Integer code;
    ResultStatus(Integer code) {
        this.code = code;
    }
}
