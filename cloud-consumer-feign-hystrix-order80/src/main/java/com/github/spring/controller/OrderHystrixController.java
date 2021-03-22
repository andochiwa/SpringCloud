package com.github.spring.controller;

import com.github.spring.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "getByIdTimeoutHandler",
            commandProperties =  {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public String getByIdTimeout(@PathVariable("id") Long id) {
        return paymentHystrixService.getByIdTimeout(id);
    }

    public String getByIdTimeoutHandler(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " getByIdTimeoutHandler";
    }
}
