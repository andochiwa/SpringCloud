package com.github.springcloud.service;

import cn.hutool.core.util.IdUtil;
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

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败到达率
    })
    public String paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        return Thread.currentThread().getName() + "\t调用成功，流水号为：" + IdUtil.randomUUID();
    }

    public String paymentCircuitBreakerFallback(Long id) {
        return "id不能为负数 Fallback";
    }
}
