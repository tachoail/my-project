package com.example.springbook.mapper;

import com.example.springbook.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    // 根据用户名查询用户信息
    @Select("select id, user_name, password, delete_flag, create_time, update_time from user_info where user_name = #{userName}")
    public UserInfo selectByName(String userName);

    // 根据用户输入信息添加用户信息
    @Insert("insert into user_info (user_name, password) values(#{userName}, #{password})")
    public int insertUser(String userName, String password);
}
