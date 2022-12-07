package com.jink.jinblog.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 响应信息
 * @date 2022/11/24 22:48:49
 */
@Data
@ToString
public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> ok()
    {
        return restResult(null, ResponseEnum.SUCCESS.code, ResponseEnum.SUCCESS.message);
    }

    public static <T> Result<T> ok(T data)
    {
        return restResult(data, ResponseEnum.SUCCESS.code, ResponseEnum.SUCCESS.message);
    }

    public static <T> Result<T> ok(T data, String msg)
    {
        return restResult(data, ResponseEnum.SUCCESS.code, msg);
    }

    public static <T> Result<T> fail()
    {
        return restResult(null, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> Result<T> fail(String msg)
    {
        return restResult(null, ResponseEnum.FAIL.code, msg);
    }

    public static <T> Result<T> fail(T data)
    {
        return restResult(data, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> Result<T> fail(T data, String msg)
    {
        return restResult(data, ResponseEnum.FAIL.code, msg);
    }

    public static <T> Result<T> fail(int code, String msg)
    {
        return restResult(null, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> Result<T> of(int code , String msg){
        return restResult(null,code,msg);
    }

    public static <T> Result<T> of(ResponseEnum responseEnum){
        return restResult(null,responseEnum.code,responseEnum.message);
    }
    private static <T> Result<T> restResult(T data, int code, String msg)
    {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public static <T> Boolean isError(Result<T> ret)
    {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(Result<T> ret)
    {
        return ResponseEnum.SUCCESS.code == ret.getCode();
    }


}
