package com.github.springcloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
