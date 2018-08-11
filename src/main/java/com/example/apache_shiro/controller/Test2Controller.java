package com.example.apache_shiro.controller;

import com.example.apache_shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Test2Controller {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/loginUser")
    public String loginUser(String username,
                            String password,
                            HttpSession session){

        try{
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);
            User user=(User)subject.getPrincipal();
            session.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            return "login";
        }

    }
}
