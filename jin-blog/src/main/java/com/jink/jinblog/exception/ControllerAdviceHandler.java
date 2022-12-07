package com.jink.jinblog.exception;

import com.jink.jinblog.result.Result;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 全局异常处理
 * @date 2022/11/27 15:30:48
 */
@Log4j2
@RestControllerAdvice
public class ControllerAdviceHandler {


    /**
     * 处理服务异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = BizException.class)
    public Result<?> errorHandler(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handleException(MethodArgumentNotValidException e) {
        StringBuilder errMsg = new StringBuilder();
        BindingResult bindResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindResult.getFieldErrors();
        fieldErrorList.forEach(fieldErrors -> {
            if (StringUtils.isNotBlank(errMsg.toString())) {
                        errMsg.append(",");
                    }
                    errMsg.append(fieldErrors.getDefaultMessage());
                }
        );
        log.error(e.getMessage(), e);
        return Result.of(400, errMsg.toString());
    }

    /**
     * 处理系统异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> errorHandler(Exception e) {
        return Result.fail(e.getMessage());
    }
}
