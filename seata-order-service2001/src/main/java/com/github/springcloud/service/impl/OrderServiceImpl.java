package com.github.springcloud.service.impl;

import com.github.springcloud.Mapper.OrderMapper;
import com.github.springcloud.entity.Order;
import com.github.springcloud.service.AccountService;
import com.github.springcloud.service.OrderService;
import com.github.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    @Override
    public void create(Order order) {
        log.info("===============开始创建订单================");
        orderMapper.insert(order);

        log.info("======开始调用库存，做扣减count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("======开始调用库存，做扣减完成");

        log.info("======开始调用账户，做扣减money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("======开始调用账户，做扣减完成");

        log.info("======开始修改订单状态");
        order.setStatus(1);
        orderMapper.updateById(order);
        log.info("======结束修改订单状态");

        log.info("===============订单创建完成================");

    }
}
