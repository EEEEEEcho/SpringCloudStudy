package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentProvider9003 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentProvider9003.class,args);
    }
}
