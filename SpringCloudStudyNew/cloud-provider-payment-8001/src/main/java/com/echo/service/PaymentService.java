package com.echo.service;

import com.echo.pojo.Payment;


public interface PaymentService {
    public int add(Payment payment);

    public Payment getPaymentById(Long id);
}
