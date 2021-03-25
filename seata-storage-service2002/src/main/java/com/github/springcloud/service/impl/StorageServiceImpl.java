package com.github.springcloud.service.impl;

import com.github.springcloud.mapper.StorageMapper;
import com.github.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.update(productId, count);
    }
}
