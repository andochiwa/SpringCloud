package com.github.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;
import com.github.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "byResourceHandler") // 按资源名限流
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按资源名限流", new Payment(100L, "serial1001"));
    }

    public CommonResult<Payment> byResourceHandler(BlockException blockException) {
        return new CommonResult<>(444, blockException.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException") // 自定义限流类
    public CommonResult<Payment> customerBlockHandler() {
        return new CommonResult<>(200, "按自定义资源限流", new Payment(101L, "serial1002"));
    }

}
