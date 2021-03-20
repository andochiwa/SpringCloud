package com.github.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 03-21-5:32
 */
@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/paymentZk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/paymentZk", String.class);
    }


}
