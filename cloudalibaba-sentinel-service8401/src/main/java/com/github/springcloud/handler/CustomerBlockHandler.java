package com.github.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
public class CustomerBlockHandler {

    public static CommonResult<Payment> handlerException(BlockException exception) {
        return new CommonResult<>(444, "按自定义资源限流--handlerException");
    }

}
