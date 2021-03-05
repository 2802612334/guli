package com.atguigu.servicebase.handler;

import com.atguigu.commonutils.Result;
import com.atguigu.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
    * 全局异常处理
    * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        return Result.error();
    }

    /*
    * 谷粒异常处理
    * */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){
        log.error(e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }

}
