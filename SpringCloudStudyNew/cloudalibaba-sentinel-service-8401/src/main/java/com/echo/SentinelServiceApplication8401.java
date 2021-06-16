package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServiceApplication8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceApplication8401.class,args);
    }
}
