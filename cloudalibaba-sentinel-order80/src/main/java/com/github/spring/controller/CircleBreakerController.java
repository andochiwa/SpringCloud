package com.github.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 配置服务熔断功能
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/{id}")
    @SentinelResource(fallback = "fallback")
    public String fallback(@PathVariable Long id) {
        String result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, String.class);
        if (id >= 10) {
            throw new IllegalArgumentException("非法参数异常");
        }
        return result;
    }

}
