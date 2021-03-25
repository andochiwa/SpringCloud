package com.github.springcloud.service;

import com.github.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @PutMapping(value = "/account/{userId}")
    CommonResult decrease(@PathVariable("userId") Long userId, @RequestParam("money") BigDecimal money);
}
