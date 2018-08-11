package com.example.apache_shiro.mapper;

import com.example.apache_shiro.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findByUserName(@Param("username")String username);

    List<User> findAll();
}
