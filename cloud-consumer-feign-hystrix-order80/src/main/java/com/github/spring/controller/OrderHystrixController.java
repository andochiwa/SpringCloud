package com.github.spring.controller;

import com.github.spring.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
@DefaultProperties(defaultFallback = "paymentGlobalFallback") // 全局Fallback，已移动到PaymentFallbackService类中处理
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/{id}")
    public String getById(@PathVariable("id") Long id) {
        return paymentHystrixService.getById(id);
    }

    @GetMapping("/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "getByIdTimeoutHandler",
//            commandProperties =  {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//            })
    @HystrixCommand
    public String getByIdTimeout(@PathVariable("id") Long id) {
        return paymentHystrixService.getByIdTimeout(id);
    }

    public String getByIdTimeoutHandler(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " 在消费端超时，getByIdTimeoutHandler===Order";
    }

    // 全局Fallback，已移动到PaymentFallbackService类中处理
    public String paymentGlobalFallback() {
        return "全局异常处理，请稍后再试";
    }
}
