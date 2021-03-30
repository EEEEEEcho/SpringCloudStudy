package com.echo.service.controller;

import com.echo.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/consumer/user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id")Long id){
        return this.restTemplate.getForObject("http://localhost:8081/user/" + id,User.class);
    }
}
