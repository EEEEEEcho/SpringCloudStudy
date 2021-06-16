package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentProvider9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentProvider9001.class,args);
    }
}
