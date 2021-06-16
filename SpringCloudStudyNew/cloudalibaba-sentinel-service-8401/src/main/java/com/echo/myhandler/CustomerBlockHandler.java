package com.echo.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.echo.pojo.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"按用户自定义的全局 handlerException----1");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"按用户自定义的全局 handlerException----2");
    }
}
