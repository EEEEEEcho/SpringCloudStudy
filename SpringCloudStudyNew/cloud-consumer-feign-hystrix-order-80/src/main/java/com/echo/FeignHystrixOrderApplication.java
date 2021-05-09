package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix  //开启Hystrix
public class FeignHystrixOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixOrderApplication.class,args);
    }
}
