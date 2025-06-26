package com.example.springbook.model;

import lombok.Data;
import java.util.Date;

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private Double price;
    private String publish;
    private Integer status; // 0:已删除 1:正常 2:不允许借阅
    private String statusCN; // 状态描述信息
    private Date createTime;
    private Date updateTime;
}
