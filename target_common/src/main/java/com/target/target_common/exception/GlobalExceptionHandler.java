package com.target.target_common.exception;

import com.target.target_common.result.BaseResult;
import com.target.target_common.result.CodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusException.class)
    public BaseResult defaultExceptionHandler(HttpServletRequest req, HttpServletResponse resp, BusException e){
        BaseResult baseResult = new BaseResult(e.getCode(), e.getMessage(), null);
        return baseResult;
    }

    @ExceptionHandler(Exception.class)
    public BaseResult defaultExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e){
        e.printStackTrace();
        BaseResult baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), CodeEnum.SYSTEM_ERROR.getMessage(), null);
        return baseResult;
    }
}
