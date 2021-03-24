package com.github.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 热点限流
    @GetMapping("/hotKey")
    @SentinelResource(value = "", blockHandler = "hotKeyHandler") // blockHandler表示备用方法
    public String hotKey(@RequestParam(value = "p1", required = false) String p1,
                         @RequestParam(value = "p2", required = false) String p2) {
        return "test hot key";
    }

    public String hotKeyHandler(String p1, String p2, BlockException blockException) {
        return "test hot key fail!";
    }
}
