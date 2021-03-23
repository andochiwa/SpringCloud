package com.github.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 03-23-17:36
 */
@RestController
public class ConfigClientController {
    @Value("${info.date}")
    private String date ;
    @Value("${info.author}")
    private String author ;
    @Value("${info.sign}")
    private String sign ;
    @Value("${info.version}")
    private String version ;
    /**
     * 获取配置信息
     */
    @RequestMapping("/getConfigInfo")
    public String getConfigInfo (){
        return date+"-"+author+"-"+sign+"-"+version ;
    }
}
