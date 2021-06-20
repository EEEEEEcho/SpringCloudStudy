package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //激活Feign
public class NacosConsumerApplication84 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication84.class,args);
    }
}
