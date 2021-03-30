package com.echo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //启用eureka服务端
public class EchoEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoEurekaApplication.class, args);
    }

}
