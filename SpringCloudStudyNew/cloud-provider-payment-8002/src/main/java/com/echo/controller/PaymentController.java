package com.echo.controller;

import com.echo.pojo.CommonResult;
import com.echo.pojo.Payment;
import com.echo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;    //添加discoveryClient


    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        log.info("insert result : " + result);
        if (result > 0){
            return new CommonResult(200,"success,the port is:" + serverPort,result);
        }
        else{
            return new CommonResult(500,"error",result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult add(@PathVariable("id")Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("query result : " + paymentById);
        System.out.println("Hhhh");
        System.out.println("HHHHH");
        if (paymentById != null){
            return new CommonResult(200,"success,the port is:" + serverPort,paymentById);
        }
        else{
            return new CommonResult(500,"error",id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){      //获得服务信息
        //得到服务清单列表
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);
        //得到CLOUD-PAYMENT-SERVICE服务下的所有服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort()
                    + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
