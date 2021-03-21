package com.github.springcloud.controller;

import com.github.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-22-7:01
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/{id}")
    public String getById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }

    @GetMapping("/payment/timeout/{id}")
    public String getByIdTimeout(@PathVariable("id") Long id) {
        return paymentService.getByIdTimeout(id);
    }

}
