package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 从客户端转发到服务器端
 * @author HAN
 * @version 1.0
 * @create 03-20-22:27
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; 集群下不能写死URL
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; // 通过微服务名称来调用

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment")
    public CommonResult<Payment> create(Payment payment) {
        log.info("access create");
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("access getPayment");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

    // 使用getForEntity，返回的是ResponseEntity对象
//    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult<>(444, "操作失败");
    }

}
