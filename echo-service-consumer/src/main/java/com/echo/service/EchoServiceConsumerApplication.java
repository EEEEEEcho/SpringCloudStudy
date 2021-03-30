package com.echo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EchoServiceConsumerApplication {

    @Bean   //将返回的RestTemplate注入到Spring容器中。使用RestTemplate进行远程调用
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EchoServiceConsumerApplication.class, args);
    }

}
