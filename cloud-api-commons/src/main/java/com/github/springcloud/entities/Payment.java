package com.github.springcloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-7:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String serial;

}
