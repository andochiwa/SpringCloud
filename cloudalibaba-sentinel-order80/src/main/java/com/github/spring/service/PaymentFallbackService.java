package com.github.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
@Service
public class PaymentFallbackService implements PaymentService{
    @Override
    public String fallback(Long id) {
        return "服务降级" + id;
    }
}
