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
public class R<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> R<T> ok()
    {
        return restResult(null, ResponseEnum.SUCCESS.code, ResponseEnum.SUCCESS.message);
    }

    public static <T> R<T> ok(T data)
    {
        return restResult(data, ResponseEnum.SUCCESS.code, ResponseEnum.SUCCESS.message);
    }

    public static <T> R<T> ok(T data, String msg)
    {
        return restResult(data, ResponseEnum.SUCCESS.code, msg);
    }

    public static <T> R<T> fail()
    {
        return restResult(null, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> R<T> fail(String msg)
    {
        return restResult(null, ResponseEnum.FAIL.code, msg);
    }

    public static <T> R<T> fail(T data)
    {
        return restResult(data, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> R<T> fail(T data, String msg)
    {
        return restResult(data, ResponseEnum.FAIL.code, msg);
    }

    public static <T> R<T> fail(int code, String msg)
    {
        return restResult(null, ResponseEnum.FAIL.code, ResponseEnum.FAIL.message);
    }

    public static <T> R<T> of(ResponseEnum responseEnum){
        return restResult(null,responseEnum.code,responseEnum.message);
    }
    private static <T> R<T> restResult(T data, int code, String msg)
    {
        R<T> apiResult = new R<>();
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

    public static <T> Boolean isError(R<T> ret)
    {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(R<T> ret)
    {
        return ResponseEnum.SUCCESS.code == ret.getCode();
    }


}
