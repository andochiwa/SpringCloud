package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PaymentMain9001 {

    public static void main(String[] args){
        SpringApplication.run(PaymentMain9001.class, args);
    }
}
