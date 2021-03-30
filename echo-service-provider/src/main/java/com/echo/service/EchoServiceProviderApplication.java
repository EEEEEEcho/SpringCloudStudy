package com.echo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //添加注解，启用eureka的客户端
public class EchoServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoServiceProviderApplication.class, args);
    }

}
