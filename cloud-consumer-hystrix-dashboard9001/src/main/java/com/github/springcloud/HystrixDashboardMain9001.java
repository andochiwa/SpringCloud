package com.github.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author HAN
 * @version 1.0
 * @create 03-23-4:21
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {

    public static void main(String[] args){
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
