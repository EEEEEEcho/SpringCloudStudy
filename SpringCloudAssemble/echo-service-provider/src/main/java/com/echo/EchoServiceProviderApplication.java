package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //说明这是一个eureka的客户端，从而将其注入到eureka注册中心
public class EchoServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoServiceProviderApplication.class, args);
    }

}
