package com.github.springcloud.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 03-22-6:57
 */
@Service
public class PaymentService {

    // 正常访问
    public String getById(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " getById";
    }

    // 异常访问
    @SneakyThrows
    public String getByIdTimeout(Long id) {
        Thread.sleep(3000);
        return "线程池: " + Thread.currentThread().getName() + " getByIdTimeout";
    }
}
