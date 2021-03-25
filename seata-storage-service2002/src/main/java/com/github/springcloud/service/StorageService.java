package com.github.springcloud.service;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
public interface StorageService {

    void decrease(Long productId, Integer count);

}
