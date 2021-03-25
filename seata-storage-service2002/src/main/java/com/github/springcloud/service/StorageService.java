package com.github.springcloud.service;

import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.entity.Storage;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
public interface StorageService {

    CommonResult<Storage> create();

}
