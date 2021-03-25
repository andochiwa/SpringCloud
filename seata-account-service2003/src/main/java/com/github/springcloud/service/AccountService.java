package com.github.springcloud.service;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);

}
