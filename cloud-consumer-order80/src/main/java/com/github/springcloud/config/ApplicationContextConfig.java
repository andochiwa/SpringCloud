package com.github.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-22:33
 */
@Configuration
// 配置负载均衡算法类，如果需要自定义算法，则需要实现ReactorServiceInstanceLoadBalancer接口
@LoadBalancerClient(name = "CLOUD-PAYMENT-SERVICE", configuration = CustomLoadBalancerConfiguration.class)
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
