package com.github.spring.controller;

import com.github.spring.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-22-7:50
 */
@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/{id}")
    public String getById(@PathVariable("id") Long id) {
        return paymentHystrixService.getById(id);
    }

    @GetMapping("/payment/timeout/{id}")
    public String getByIdTimeout(@PathVariable("id") Long id) {
        return paymentHystrixService.getByIdTimeout(id);
    }
}
