package com.jink.jinblog.exception;

import com.jink.jinblog.result.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/26 17:34:29
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = ResponseEnum.FAIL.getCode();

    /**
     * 错误信息
     */
    private String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
}
