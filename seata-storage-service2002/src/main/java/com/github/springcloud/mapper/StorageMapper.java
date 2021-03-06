package com.github.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Mapper
public interface StorageMapper {

    void update(@Param("productId") Long productId,@Param("count") Integer count);

}
