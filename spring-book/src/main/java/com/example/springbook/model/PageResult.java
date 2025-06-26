package com.example.springbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Integer total; //总记录数
    private List<T> records; // 当前页数据
    private PageRequest pageRequest;
}
