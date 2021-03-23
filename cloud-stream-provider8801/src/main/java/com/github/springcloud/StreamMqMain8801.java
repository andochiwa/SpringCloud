package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author HAN
 * @version 1.0
 * @create 03-24-1:05
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StreamMqMain8801 {

    public static void main(String[] args){
        SpringApplication.run(StreamMqMain8801.class, args);
    }
}
