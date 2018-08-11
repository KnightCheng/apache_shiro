package com.example.apache_shiro.controller;


import com.example.apache_shiro.model.User;
import com.example.apache_shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/hello")
    public String testIndex(){
        return "hello springboot";
    }

    @RequestMapping(value = "/data")
    public User getData(){
        return userService.findByUserName("admin");
    }

    @RequestMapping(value = "/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/test")
    public User findata(){
        return userService.findByUserName("admin");
    }





}
