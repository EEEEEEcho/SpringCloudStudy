package com.echo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController //@RestController注解标注的controller都应该返回json类型的数据
@RequestMapping("/hello")
public class HelloController {

    @Autowired          //自动注入JdbcConfiguration中配置的JDBC数据源
    private DataSource dataSource;

    @GetMapping("/show")
    public String test(){
        System.out.println(dataSource == null);
        return "Hello Spring boot 1";
    }
}
