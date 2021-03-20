package com.github.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author HAN
 * @version 1.0
 * @create 03-21-6:15
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class PaymentMain8006 {

    public static void main(String[] args){
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
