package com.echo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //nacos也是使用restTemplate进行远程调用
    @Bean
    @LoadBalanced   //一定要加loadbalance
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
