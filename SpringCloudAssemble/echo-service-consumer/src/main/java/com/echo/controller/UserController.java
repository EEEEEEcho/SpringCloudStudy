package com.echo.controller;

import com.echo.client.UserClient;
import com.echo.pojo.User;
import com.netflix.appinfo.InstanceInfo;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/consumer/user")
// @DefaultProperties(defaultFallback = "fallBackMethod")使用 Feign之后就不需要单独使用Hystrix进行熔断了，需要用Feign的方式
public class UserController {
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired

//    private DiscoveryClient discoveryClient;    //用来拉取所有的服务提供方，包含了拉取的所有服务信息,注意，导的是spring中的包
    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "queryUserByIdFallBack")   //指定熔断后触发的方法
    public String queryUserById(@PathVariable("id")Long id){
        //通过服务提供方的ID来找到所有的服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
////        System.out.println(instances);
//        ServiceInstance serviceInstance = instances.get(0);
//        return this.restTemplate.getForObject("http://" +
//                serviceInstance.getHost()
//                + ":"
//                + serviceInstance.getPort()
//                + "/user/"
//                + id,User.class);

        //使用负载均衡后要用服务名进行调用
//        return this.restTemplate.getForObject("http://service-provider/user/" + id , String.class);

        /*   使用Feign进行远程调用    */
        return userClient.queryUserById(id).toString();
    }

    /**
     * 这是queryUserById的熔断方法，当使用hystrix之后的queryUserById请求微服务
     * 出现hystrix线程池满，或者网络延时之后，会调用这个callBack方法
     * @param id
     * @return
     */
    public String queryUserByIdFallBack(Long id){
        return "服务器正忙。稍后再试";
    }

    /**
     * 全局的熔断方法，不能给它传递参数
     * @return
     */
    public String fallBackMethod(){
        return "服务器正忙，稍后再试，全局的熔断回调方法";
    }
}
