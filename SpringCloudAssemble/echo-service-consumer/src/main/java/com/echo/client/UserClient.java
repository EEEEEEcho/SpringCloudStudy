package com.echo.client;

import com.echo.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-provider",fallback = UserClientFallBack.class)    //声明这是一个Feign的客户端，要远程调用的服务是service-provider。熔断的实现类是UserClientFallBack
public interface UserClient {

    @GetMapping("/user/{id}")       //声明调用微服务的哪个接口 /user/{id}
    public User queryUserById(@PathVariable("id") Long id);

}
