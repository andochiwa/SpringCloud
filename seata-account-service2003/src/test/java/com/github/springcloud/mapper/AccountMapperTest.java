package com.github.springcloud.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    void decrease() {
        accountMapper.decrease(1L, new BigDecimal(1));
    }
}