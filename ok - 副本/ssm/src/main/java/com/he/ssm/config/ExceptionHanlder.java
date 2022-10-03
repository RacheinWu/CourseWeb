package com.he.ssm.config;

import com.he.ssm.bean.ResultT;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author itaem
 * date:2018-12-27 2018/12/27:18:06
 */
@RestControllerAdvice
public class ExceptionHanlder {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultT argInvalid(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        return ResultT.errorWithMsg(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultT noRequestBody(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        return ResultT.errorWithMsg(ex.getMessage());

    }

    @ExceptionHandler(RuntimeException.class)
    public ResultT handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultT.errorWithMsg(ex.getMessage());
    }



}
