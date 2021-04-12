package com.echo.dao;

import com.echo.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
    public int add(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
