package com.github.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
@RestController
public class FlowLimitController {

    @GetMapping("/getA")
    public String getA() {
        return "A";
    }

    @GetMapping("/getB")
    public String getB() {
        return "B";
    }
}
