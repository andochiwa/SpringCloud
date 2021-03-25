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
public class SeataStorageMain2002 {

    public static void main(String[] args){
        SpringApplication.run(SeataStorageMain2002.class, args);
    }
}
