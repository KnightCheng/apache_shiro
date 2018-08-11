package com.example.apache_shiro.service;

import com.example.apache_shiro.mapper.UserMapper;
import com.example.apache_shiro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
