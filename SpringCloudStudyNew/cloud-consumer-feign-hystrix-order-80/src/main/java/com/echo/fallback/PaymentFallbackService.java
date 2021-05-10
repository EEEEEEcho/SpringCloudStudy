package com.echo.fallback;

import com.echo.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "***PaymentFallbackService***paymentInfoOk***fallback***";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "***PaymentFallbackService***paymentInfoTimeout***fallback***";
    }
}
