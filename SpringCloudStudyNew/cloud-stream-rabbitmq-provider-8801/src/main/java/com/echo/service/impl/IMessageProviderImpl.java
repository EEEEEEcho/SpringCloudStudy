package com.echo.service.impl;

import com.echo.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

@EnableBinding(Source.class)   //定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;  //消息的发送管道

    @Override
    public String send() {
        String message = UUID.randomUUID().toString().replace("-","");
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("*****************Serial : " + message);
        return null;
    }
}
