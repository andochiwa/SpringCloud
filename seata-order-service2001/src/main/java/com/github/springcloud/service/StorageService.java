package com.github.springcloud.service;

import com.github.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @PutMapping(value = "/storage/{productId}/{count}")
    void decrease(@PathVariable("productId") Long productId, @PathVariable("count") Integer count);
}
