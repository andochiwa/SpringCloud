package com.github.spring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HAN
 * @version 1.0
 * @create 03-22-7:48
 */
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/{id}")
    String getById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout/{id}")
    String getByIdTimeout(@PathVariable("id") Long id);
}
