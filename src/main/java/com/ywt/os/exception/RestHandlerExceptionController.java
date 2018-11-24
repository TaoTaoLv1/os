package com.ywt.os.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: YwT
 * @description: 全局异常处理
 * @create: 2018-11-24 19:52
 **/
@RestControllerAdvice
@ResponseBody
public class RestHandlerExceptionController {


    @ExceptionHandler(value = IllegalArgumentException.class)
    public String IllegalArgument(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String NullPointer(Exception e){
        return e.getMessage();
    }


}
