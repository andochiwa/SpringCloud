package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author HAN
 * @version 1.0
 * @create 03-23-17:31
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigClientMain3355 {

    public static void main(String[] args){
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
