package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;
import com.github.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-7:40
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment")
    public CommonResult<Boolean> create(Payment payment) {
        boolean save = paymentService.save(payment);
        log.info("******插入结果：" + save);
        if (save) {
            return new CommonResult<>(200, "插入数据库成功", save);
        }
        return new CommonResult<>(444, "插入数据库失败", null);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return new CommonResult<>(200, "获取数据成功", payment);
        }
        return new CommonResult<>(444, "id[" + id + "]没有对应记录", payment);
    }

}
