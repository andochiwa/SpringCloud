package com.github.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author HAN
 * @version 1.0
 * @create 03-23-5:45
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class GatewayMain9527 {

    public static void main(String[] args){
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
