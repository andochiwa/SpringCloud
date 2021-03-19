package com.github.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.springcloud.dao.PaymentDao;
import com.github.springcloud.entities.Payment;
import com.github.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-7:36
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentService {
}
