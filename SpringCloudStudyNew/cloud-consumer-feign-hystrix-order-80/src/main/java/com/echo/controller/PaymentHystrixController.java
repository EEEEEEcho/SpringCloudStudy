package com.echo.controller;

import com.echo.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class PaymentHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    //@HystrixCommand //添加注解但是不单独指明熔断方法，则标识，改方法也可以触发服务降级，并且使用全局fallback
    public String paymentInfoOk(@PathVariable("id") Integer id){
        //int x = 1/0;
        return paymentHystrixService.paymentInfoOk(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",   //不使用全局fallback，使用指定的fallback
//            commandProperties = {
//                    //设置自身调用超时时间的峰值，峰值内可以正常运行，超过了需要有兜底的方法处理，作服务降级fallback
//                    //设置这个线程的超时时间是1.5秒钟,而服务提供方的提供服务的时间是2秒，超时时间是3秒，所以会熔断
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1500")
//            })   //如果该方法出现异常/超时，就会调用fallback中指定的方法
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        int age = 1 / 0;
        return paymentHystrixService.paymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "我是消费者80，服务提供方的服务繁忙，请10秒钟之后再试，或者自己运行出错，请检查自己。。。。";
    }

    //下面是全局fallback方法
    public String paymentGlobalFallbackMethod(){
        return "Global异常信息处理，请稍后再试。";
    }
}
