package com.echo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderNacosController {
    //原来进行远程调用时，需要定义远程调用的URL
    //public static final String SERVER_URL = "http://nacos-payment-provider";
    //现在使用Nacos之后，因为在application.yml中定义了 service-url, 所以不用在此处定义了
    //而是使用@Value,将yml中的值注入
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/consumer/payment/{id}")
    public String paymentInfo(@PathVariable("id") String id){
        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id ,String.class);
    }
}
