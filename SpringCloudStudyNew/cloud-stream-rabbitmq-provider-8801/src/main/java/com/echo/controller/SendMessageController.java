package com.echo.controller;

import com.echo.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private IMessageProvider provider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return provider.send();
    }
}
