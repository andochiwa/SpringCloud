package com.github.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HAN
 * @version 1.0
 * @create 03-20-7:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {


    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
