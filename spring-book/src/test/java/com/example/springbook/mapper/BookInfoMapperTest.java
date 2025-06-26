package com.example.springbook.mapper;

import com.example.springbook.model.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookInfoMapperTest {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Test
    void selectAllBook() {
        bookInfoMapper.selectAllBook();
    }


    @Test
    void insertBook() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName("图书5");
        bookInfo.setAuthor("作者5");
        bookInfo.setCount(11);
        bookInfo.setPrice(12.5);
        bookInfo.setPublish("出版社5");
        bookInfo.setStatus(1);
        bookInfoMapper.insertBook(bookInfo);
    }

    @Test
    void updateBook() {
//        // 修改图书信息
//        BookInfo bookInfo = new BookInfo();
//        bookInfo.setId(2);
//        bookInfo.setBookName("图书222");
//        bookInfoMapper.updateBook(bookInfo);

        // 删除图书信息
        BookInfo bookInfo1 = new BookInfo();
        bookInfo1.setId(1);
        bookInfo1.setStatus(0);
        bookInfoMapper.updateBook(bookInfo1);
    }
}