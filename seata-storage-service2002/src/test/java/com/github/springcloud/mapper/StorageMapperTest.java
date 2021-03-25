package com.github.springcloud.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StorageMapperTest {

    @Autowired
    StorageMapper storageMapper;

    @Test
    void update() {
        storageMapper.update(1L,1);
    }
}