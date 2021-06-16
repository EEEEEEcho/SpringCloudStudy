package com.echo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")      //因为配置中心将配置以rest风格进行了暴漏，所以配置消费方，要进行访问
    public String getConfigInfo(){
        return "ServerPort : \n\t" + serverPort + "\n" + "ConfigInfo : \n\t" + configInfo;
    }
}