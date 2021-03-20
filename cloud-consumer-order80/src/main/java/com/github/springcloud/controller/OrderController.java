package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 从客户端转发到服务器端
 * @author HAN
 * @version 1.0
 * @create 03-20-22:27
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment")
    public CommonResult<Payment> create(Payment payment) {
        log.info("access create");
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("access getPayment");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

}
