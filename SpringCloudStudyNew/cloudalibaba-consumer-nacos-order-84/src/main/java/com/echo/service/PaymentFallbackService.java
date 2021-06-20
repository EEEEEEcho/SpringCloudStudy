package com.echo.service;

import com.echo.pojo.CommonResult;
import com.echo.pojo.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回，----paymentFallbackService",
                new Payment(id,"errorSerial"));
    }
}
