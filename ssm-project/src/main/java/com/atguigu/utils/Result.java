package com.atguigu.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-11
 * @Description: 全局统一返回类
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.data = data;
        }
        return result;
    }

    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.code = resultCodeEnum.getCode();
        result.message = resultCodeEnum.getMessage();
        return result;
    }

    // 操作成功
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }
}
