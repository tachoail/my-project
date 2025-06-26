package com.example.springbook.model;

import lombok.Data;

@Data
public class PageRequest {
    private Integer currentPage = 1; // 当前页
    private Integer pageSize = 5; // 每页显示条数
    private int offset; // 索引
    // 计算索引
    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
