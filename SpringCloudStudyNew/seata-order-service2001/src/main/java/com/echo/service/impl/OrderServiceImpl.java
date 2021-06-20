package com.echo.service.impl;

import com.echo.dao.OrderDao;
import com.echo.domain.Order;
import com.echo.service.AccountService;
import com.echo.service.OrderService;
import com.echo.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;


    @Override
    //name为该全局事务的名字，可以随便起，rollbackFor = Exception.class表示发生任何异常都回滚
    @GlobalTransactional(name = "echo-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //1.新建订单
        log.info("-----------开始新建订单----------");
        orderDao.create(order);
        //2.调用库存服务扣减库存
        log.info("-----------订单微服务开始调用库存微服务，做扣减Count-----------");
        storageService.decreaseStorage(order.getProductId(),order.getCount());
        log.info("-----------订单微服务开始调用库存微服务，扣减完成-----------");
        //3.调用账户服务扣减账户余额
        log.info("-----------订单微服务开始调用账户微服务，减少用户账户里的Money-----------");
        accountService.decreaseStorage(order.getUserId(),order.getMoney());
        log.info("-----------订单微服务开始调用账户微服务，扣减完成-----------");

        //4.修改用户的订单的状态，从0到1，1代表已经完成
        log.info("-----------修改订单状态开始----------");
        orderDao.update(order.getUserId(), 0);
        log.info("-----------修改订单状态完成----------");


        log.info("订单已完成");
    }
}
