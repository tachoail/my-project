package com.example.springbook.service;

import com.example.springbook.enums.BookStatus;
import com.example.springbook.mapper.BookInfoMapper;
import com.example.springbook.model.BookInfo;
import com.example.springbook.model.PageRequest;
import com.example.springbook.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    public Integer insertBook(BookInfo bookInfo) {
        return bookInfoMapper.insertBook(bookInfo);
    }

    public List<BookInfo> selectAllBook() {
        return bookInfoMapper.selectAllBook();
    }


    public PageResult<BookInfo> getListByPage(PageRequest pageRequest) {
        // 获取总记录数
        Integer total = bookInfoMapper.count();
        // 获取当前页记录
        List<BookInfo> bookInfoList= bookInfoMapper.selectByPage(pageRequest.getOffset(), pageRequest.getPageSize());
        // 处理状态
        for (BookInfo bookInfo: bookInfoList) {
            bookInfo.setStatusCN(BookStatus.getDescByCode(bookInfo.getStatus()).getDesc());
        }
        return new PageResult(total, bookInfoList, pageRequest);
    }

    public BookInfo selectById(Integer id) {
        return bookInfoMapper.selectById(id);
    }

    public int updateById(BookInfo bookInfo) {
        return bookInfoMapper.updateBook(bookInfo);
    }

    public int batchDeleteBook(List<Integer> ids) {
        return bookInfoMapper.batchDeleteBook(ids);
    }
}
