package com.github.springcloud.controller;

import com.github.springcloud.entities.Account;
import com.github.springcloud.entities.CommonResult;
import com.github.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping(value = "/account/{userId}")
    public CommonResult<Account> decrease(@PathVariable("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult<>(200, "扣减money成功");
    }

}
