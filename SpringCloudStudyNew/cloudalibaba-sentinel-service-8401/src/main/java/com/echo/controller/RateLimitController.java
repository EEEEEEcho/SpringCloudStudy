package com.echo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.echo.myhandler.CustomerBlockHandler;
import com.echo.pojo.CommonResult;
import com.echo.pojo.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    //为了测试限流规则是根据@GetMapping中的值还是@SentinelResource中的值,定义为byResourcex
    @SentinelResource(value = "byResourcex",blockHandler = "handleException")
    //结果发现是根据@SentinelResource中的值
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试成功",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult(444,blockException.getClass().getCanonicalName() + "\t" + "服务不可用");
    }

    @GetMapping("/reteLimit/byUrl")
    @SentinelResource(value = "byUrl")  //没有写blockHandler后会按照Sentinel的默认兜底规则处理
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流测试成功",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, //自定义的全局handlerException类
            blockHandler = "handlerException2"      //全局handlerException类中的处理异常的方法
    )
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义限流测试成功",new Payment(2020L,"serial003"));
    }
}
