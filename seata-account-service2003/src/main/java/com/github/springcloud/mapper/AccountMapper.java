package com.github.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Mapper
public interface AccountMapper {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
