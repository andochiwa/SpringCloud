package com.github.springcloud.Mapper;

import com.github.springcloud.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void testInsert() {
        Order order = new Order(null, 2L, 2L, 2, new BigDecimal(1), 1);
        orderMapper.insert(order);
    }

}