package com.dhu.advice;

import com.dhu.tools.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result<Object> doException(Exception e){
        return new Result<>(Result.EXCEPTION,null,e.getMessage());
    }
}