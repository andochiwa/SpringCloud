package com.github.springcloud.service.impl;

import com.github.springcloud.Mapper.OrderMapper;
import com.github.springcloud.entities.Order;
import com.github.springcloud.service.AccountService;
import com.github.springcloud.service.OrderService;
import com.github.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
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
    // 可以自动全局处理分布式事务，可以做到在整条链路上布满事务，不会因为某条链路出错而无法回滚
    @GlobalTransactional(name = "OrderTransaction", rollbackFor = Exception.class)
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
