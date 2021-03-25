package com.github.springcloud.service.impl;

import com.github.springcloud.mapper.AccountMapper;
import com.github.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
