package com.github.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "getByIdTimeoutHandler",
            commandProperties =  {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String getByIdTimeout(Long id) {
        Thread.sleep(3000);
        return "线程池: " + Thread.currentThread().getName() + " getByIdTimeout";
    }

    public String getByIdTimeoutHandler(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " 在生产端超时，getByIdTimeoutHandler";
    }
}
