package com.atguigu.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-11
 * @Description: 统一返回结果状态信息的枚举类
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOT_LOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed");

    private final Integer code;
    private final String message;
}
