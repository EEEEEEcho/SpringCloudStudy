package com.echo.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问肯定没问题的方法
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " PaymentInfo_OK,id: " + id + "\t" + "哈哈";
    }

    /**
     * 会超时的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",
            commandProperties = {
            //设置自身调用超时时间的峰值，峰值内可以正常运行，超过了需要有兜底的方法处理，作服务降级fallback
            //设置这个线程的超时时间是3秒钟
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })   //如果该方法出现异常/超时，就会调用fallback中指定的方法
    public String paymentInfoTimeout(Integer id){
        /**
         * 约定 3秒钟，正常。
         * 超过3秒钟，异常
         * 一旦调用服务方法失败并抛出了错误信息之后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中指定的方法
         */
        int timeNumber = 2;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //直接抛出异常
        //int x = 1 / 0;
        return "线程池: " + Thread.currentThread().getName() + " PaymentInfo_Timeout,id: " + id
                + "\t" + "哈哈 耗时:"  + timeNumber + "秒钟";

    }


    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeoutHandler,id: " + id
                + "\t" + "系统繁忙或运行出错，请稍后再试";
    }


    //===========服务熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    //是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    //请求次数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
                    //时间窗口期
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    //失败率达到多少后，跳闸
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
                    //总结一下就是在10秒的时间窗口期，以10秒中为一个时间单位，如果在10请求中，有60%失败，则跳闸
            }
    )
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("****ID 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id")Integer id){
        return "ID 不能为负数，请稍后再试，/(T o T)/~~   id:" + id;
    }
}
