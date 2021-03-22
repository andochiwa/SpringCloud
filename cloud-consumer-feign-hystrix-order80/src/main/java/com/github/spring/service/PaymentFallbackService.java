package com.github.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 03-22-22:31
 */
@Service
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String getById(Long id) {
        return "-----getById fall back";
    }

    @Override
    public String getByIdTimeout(Long id) {
        return "-----getByIdTimeout fall back";
    }
}
