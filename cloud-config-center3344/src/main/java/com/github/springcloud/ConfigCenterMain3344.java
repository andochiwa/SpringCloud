package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author HAN
 * @version 1.0
 * @create 03-23-8:04
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigServer
public class ConfigCenterMain3344 {

    public static void main(String[] args){
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
