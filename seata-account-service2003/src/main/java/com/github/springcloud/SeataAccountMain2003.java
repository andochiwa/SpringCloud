package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@SpringBootApplication
@EnableFeignClients
public class SeataAccountMain2003 {

    public static void main(String[] args){
        SpringApplication.run(SeataAccountMain2003.class, args);
    }
}
