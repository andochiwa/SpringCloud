package com.github.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author HAN
 * @version 1.0
 * @create 03-21-4:41
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/paymentZk")
    public String paymentZk() {
        return "SpringCloud with zookeeper:" + UUID.randomUUID().toString();
    }

}
