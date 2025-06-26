package com.example.springbook.mapper;

import com.example.springbook.model.BookInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookInfoMapper {
    /**
     * 获取图书列表
     */
    @Select("select id, book_name, author, count, price, publish, `status`, create_time, update_time from book_info ")
    List<BookInfo> selectAllBook();

    /**
     * 插入新的图书信息
     */
    @Insert("insert into book_info (book_name, author, count, price, publish, `status`) " +
            "values (#{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status})")
    Integer insertBook(BookInfo bookInfo);

    /**
     * 根据图书id修改图书信息
     */
    Integer updateBook(BookInfo bookInfo);

    /**
     * 获取当前页图书数据
     */
    @Select("select id, book_name, author, count, price, publish, `status`, create_time, update_time from book_info" +
            " where status != 0" +
            " order by id desc" +
            " limit #{offset}, #{pageSize}")
    List<BookInfo> selectByPage(int offset, Integer pageSize);

    /**
     * 获取未被删除的所有图书数量
     */
    @Select("select count(1) from book_info where status != 0")
    Integer count();

    @Select("select id, book_name, author, count, price, publish, `status`, create_time, update_time from book_info" +
            " where id = #{id} and status != 0")
    BookInfo selectById(Integer id);

    int batchDeleteBook(List<Integer> ids);
}
