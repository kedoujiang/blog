package com.jink.jinblog.result;

import lombok.Getter;
import lombok.ToString;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/24 22:52:37
 */
@Getter
@ToString
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(200, "OK"),

    /**
     * 失败
     */
    FAIL(500, "FAIL"),
    GATEWAY_SYS_ERROR(502, "系统网关错误"),
    PERMISSION_DENIED(403, "未进行授权"),
    ACCOUNT_NOT_EXIST(404, "账号信息不存在"),
    LOGIN_PASSWORD_ERROR(400, "用户名或密码错误"),
    ACCOUNT_EXPIRED(400, "账号已过期"),
    ACCOUNT_LOCKED(405, "账号已被锁定"),
    ACCOUNT_CREDENTIAL_EXPIRED(402, "用户凭证已失效"),
    ACCOUNT_DISABLE(404, "账号已被禁用");

    final Integer code;
    final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;

    }
}
