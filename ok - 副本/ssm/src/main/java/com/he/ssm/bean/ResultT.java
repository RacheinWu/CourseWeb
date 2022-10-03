package com.he.ssm.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultT<T> {

    public static final int    SERVICE_ERROR = 500;
    public static final int    ARGS_ERROR    = 999;
    public static final int    NO_AUTH       = 403;
    public static final int    SUCCESS       = 200;
    public static final int    LIMIT_ERROR       = 888;
    /**
     * code 0 - 正确； 非0为错误码
     */
    private             int    code          = 200;
    private             String msg;
    private             T      data;


    public ResultT() {
    }
    public <D extends T> ResultT(int code ,D data) {
        this.code = code;
        this.data = data;
    }
    public <D extends T> ResultT(D data) {
        this.data = data;
    }

    public ResultT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static <T> ResultT<T> errorWithCodeAndMsg(int code, String msg) {
        return new ResultT<T>(code, msg);
    }

    public static <T> ResultT<T> success() {
        return new ResultT<T>();
    }

    public static <T> ResultT<T> successWithData(T data) {
        return new ResultT<>(data);
    }

    public static <T> ResultT<T> errorWithArgs(String msg) {
        return new ResultT<T>(ARGS_ERROR, msg);
    }

    public static <T> ResultT<T> errorWithMsg(String msg) {
        return new ResultT<T>(SERVICE_ERROR, StringUtils.isNotBlank(msg) ? msg : "网络异常，请稍后再试!");
    }
    public static <T> ResultT<T> errorWithData(T data) {
        return new ResultT<>(LIMIT_ERROR,data);
    }
    public static <T> ResultT<T> error() {
        return errorWithMsg(null);
    }


    public static <T> ResultT<T> noAuth(String msg) {
        return new ResultT<T>(NO_AUTH, StringUtils.isBlank(msg) ? "无授权!" : msg);
    }

    public static <T> ResultT<T> noAuth() {
        return noAuth(null);
    }

    public static <T> ResultT<T> tokenInvalid() {
        return new ResultT<T>(NO_AUTH, "签名验证失败或token头缺失!");
    }

    public boolean fail() {
        return code != 0;
    }

}
