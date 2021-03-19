package com.github.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-7:23
 */
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {


}
