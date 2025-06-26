package com.example.springbook.controller;

import com.example.springbook.model.BookInfo;

import com.example.springbook.model.PageRequest;
import com.example.springbook.model.PageResult;
import com.example.springbook.model.Result;
import com.example.springbook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 添加图书
     */
    @RequestMapping(value = "/addBook", produces =  "application/json")
    public Result<String> addBook(BookInfo bookInfo) {
        log.info("添加图书，接收到参数{}", bookInfo);
        // 参数校验
        if(!StringUtils.hasLength(bookInfo.getBookName()) ||
                !StringUtils.hasLength(bookInfo.getAuthor()) ||
                bookInfo.getCount() == null || bookInfo.getCount() < 0 ||
                bookInfo.getPrice() == null || bookInfo.getPrice() < 0 ||
                !StringUtils.hasLength(bookInfo.getPublish()) ||
                bookInfo.getStatus() == null) {
            return Result.fail("输入参数不合法!");
        }
        // 添加图书
        try {
            Integer result = bookService.insertBook(bookInfo);
            if(result > 0) {
                return Result.success("");
            }
        }catch (Exception e) {
            log.error("添加图书失败，e", e);
        }
        return Result.fail("添加失败!");
    }

    /**
     * 查询图书列表
     */
    @RequestMapping("/getListByPage")
    public Result<PageResult<BookInfo>> getListByPage(PageRequest pageRequest) {
        log.info("获取图书列表, 接收到参数pageRequest:{}", pageRequest);
        PageResult<BookInfo> pageResult = bookService.getListByPage(pageRequest);
        if(pageResult == null) {
            return Result.fail("获取图书列表失败!");
        }
        return Result.success(pageResult);
    }

    /**
     * 根据图书id获取图书信息
     */
    @RequestMapping("/queryById")
    public Result<BookInfo> queryById(Integer bookId) {
        log.info("根据图书id获取图书信息, 接收参数id:{}", bookId);
        // 参数校验
        if(bookId == null || bookId <= 0) {
            return Result.fail("参数错误!");
        }
        try {
            BookInfo bookInfo = bookService.selectById(bookId);
            if(bookInfo != null) {
                return Result.success(bookInfo);
            }else {
                return Result.fail("获取图书信息失败!");
            }
        }catch (Exception e) {
            log.info("获取图书信息失败, e", e);
        }
        return Result.fail("获取图书信息失败!");
    }
    /**
     * 修改图书信息
     */
    @RequestMapping("/updateBook")
    public Result<String> updateBook(BookInfo bookInfo) {
        log.info("修改图书信息, 获取参数bookInfo:{}", bookInfo);
        // 参数校验
        if(bookInfo.getId() == null || bookInfo.getId() < 0) {
            return Result.fail("图书id有误!");
        }
        // 修改图书
        int result = bookService.updateById(bookInfo);
        if(result > 0) {
            return Result.success("");
        }else {
            return Result.fail("修改失败!");
        }
    }
    /**
     * 删除图书信息
     */
    @RequestMapping("/deleteBook")
    public Result<String> deleteBook(BookInfo bookInfo) {
        log.info("删除图书信息, 获取参数bookInfo:{}", bookInfo);
        return this.updateBook(bookInfo);
    }
    /**
     * 批量删除图书信息
     */
    @RequestMapping(value = "/batchDeleteBook", produces = "application/json")
    public Boolean batchDeleteBook(@RequestParam List<Integer> ids) {
        log.info("批量删除图书, ids:{}", ids);
        try {
            int result = bookService.batchDeleteBook(ids);
        }catch (Exception e) {
            log.error("批量删除图书异常, e", e);
            return false;
        }
        return true;
    }
}
