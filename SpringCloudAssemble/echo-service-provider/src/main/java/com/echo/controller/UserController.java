package com.echo.controller;

import com.echo.pojo.User;
import com.echo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String queryAllUser(){
        List<User> users = userService.queryAllUser();
        return Arrays.toString(users.toArray());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id") Long id){
        return userService.queryUserById(id);
    }
}
