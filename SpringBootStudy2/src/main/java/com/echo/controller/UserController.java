package com.echo.controller;

import com.echo.pojo.User;
import com.echo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id")Long id){
        //注意，该方法使用了GetMapping注解，是使用restful风格进行参数的接受，参数接收时要使用@PathVariable注解
        //如果该方法使用的是RequestMapping注解，即使用 ? 拼接的参数，需要使用@RequestParam注解来标记参数
        User user = this.userService.queryUserById(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello Controller";
    }

    @GetMapping("/all")
    public String queryUserAll(Model model){
        List<User> users = this.userService.queryUserAll();
        model.addAttribute("users",users);
        return "users";
    }
}
