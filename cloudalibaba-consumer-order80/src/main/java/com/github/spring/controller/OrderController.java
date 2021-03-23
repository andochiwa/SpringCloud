package com.github.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/24
 */
@RestController
public class OrderController {

    private static final String SERVER_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/{id}")
    public String getId(@PathVariable("id") Long id) {
        return restTemplate.getForObject(SERVER_URL + "/payment/" + id, String.class);
    }

}
