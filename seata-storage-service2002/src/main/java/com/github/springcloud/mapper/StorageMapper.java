package com.github.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.springcloud.entities.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    void update(Long productId, Integer count);

}
