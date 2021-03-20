package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;
import com.github.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Value("${server.port}")
    private String serverPort;

    // 不要忘记RequestBody注解，因为Order客户端传过来的是Json串，需要解析
    @PostMapping("/payment")
    public CommonResult<Boolean> create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        log.info("******插入结果：" + save);
        if (save) {
            return new CommonResult<>(200, "插入数据库成功, serverPort: " + serverPort, save);
        }
        return new CommonResult<>(444, "插入数据库失败", null);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        log.info("成功进入getPayment");
        if (payment != null) {
            return new CommonResult<>(200, "获取数据成功, serverPort:" + serverPort, payment);
        }
        return new CommonResult<>(444, "id[" + id + "]没有对应记录", payment);
    }

}
