package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entity.Order;
import com.github.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public CommonResult<Order> create(Order order) {
        orderService.create(order);
        return new CommonResult<Order>(200, "订单创建成功", order);
    }



}
