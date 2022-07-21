package com.soft.forum.Utils;

import lombok.Getter;

@Getter
public enum resCodeEnum {
    SUCCESS(true,200, "成功"),
    FAIL(false, 201, "失败"),
    UNKNOW_REASON(false,208,"未知错误"),
    LOGIN_ERROR(false, 202, "用户名或密码错误"),
    LOGIN_AUTH(false, 203, "未登陆"),
    LOGIN_SUCCESS(true, 204, "登陆成功"),
    PERMISSION(false, 205, "没有权限"),
    CODE_ERROR(false, 206, "验证码错误!"),
    TOKEN_ERROR(false, 207, "Token无效!"),
    ACTIVATE_ERROR(false, 207, "已注册未激活"),
    EMAIL_ERROR(false, 208, "邮件服务器错误");

    private final Integer code;
    private final String message;
    private Boolean status;

    resCodeEnum(Boolean status, Integer code, String message){
        this.code = code;
        this.message = message;
        this.status = status;
    }
}


