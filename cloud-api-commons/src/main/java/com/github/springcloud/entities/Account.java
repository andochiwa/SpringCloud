package com.github.springcloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/3/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private BigDecimal total;

    private BigDecimal used;

    private BigDecimal residue;
}
