package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SentinelMain8401 {

    public static void main(String[] args){
        SpringApplication.run(SentinelMain8401.class, args);
    }
}
