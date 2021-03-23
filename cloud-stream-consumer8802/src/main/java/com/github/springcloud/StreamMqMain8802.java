package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author HAN
 * @version 1.0
 * @create 03-24-1:36
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StreamMqMain8802 {

    public static void main(String[] args){
        SpringApplication.run(StreamMqMain8802.class, args);
    }
}
