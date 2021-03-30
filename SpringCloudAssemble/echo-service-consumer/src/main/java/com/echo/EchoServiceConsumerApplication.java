package com.echo;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient  //启用eureka客户端，从注册中心获取服务
//@EnableCircuitBreaker   //启用hystrix熔断器
@SpringCloudApplication //相当于上面三个注解
@EnableFeignClients // 启用Feign组件
public class EchoServiceConsumerApplication {

//    @Bean   //将返回的RestTemplate注入到Spring容器中。使用RestTemplate进行远程调用
//    @LoadBalanced   //开启负载均衡。eureka中已经引入了ribbion的依赖，因此既不需要启动器，也不需要配置
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
    //使用Feign之后就不需要使用RestTemplate进行远程调用了

    public static void main(String[] args) {
        SpringApplication.run(EchoServiceConsumerApplication.class, args);
    }

}
