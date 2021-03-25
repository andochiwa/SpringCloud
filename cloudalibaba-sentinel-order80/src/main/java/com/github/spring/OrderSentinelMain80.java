package com.github.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
public class OrderSentinelMain80 {

    public static void main(String[] args){
        SpringApplication.run(OrderSentinelMain80.class, args);
    }
}
