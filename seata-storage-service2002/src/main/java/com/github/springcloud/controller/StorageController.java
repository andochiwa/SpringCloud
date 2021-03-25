package com.github.springcloud.controller;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entities.Storage;
import com.github.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PutMapping(value = "/storage/{productId}/{count}")
    public CommonResult<Storage> decrease(@PathVariable("productId") Long productId, @PathVariable("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult<>(200, "成功扣减库存");
    }

}
