package com.echo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced   //负载均衡，根据服务名，找到不同的主机名，这个注解是开启Ribbon的负载均衡功能的注解
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
