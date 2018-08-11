package com.example.apache_shiro.service;

import com.example.apache_shiro.model.User;

import java.util.List;

public interface UserService {
    User findByUserName(String username);

    List<User> findAll();
}
