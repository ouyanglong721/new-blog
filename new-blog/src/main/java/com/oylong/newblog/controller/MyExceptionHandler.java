package com.oylong.newblog.controller;


import com.oylong.newblog.entity.Result;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        Result result = ResultUtil.buildUnSuccessResult();

        if (e instanceof CustomException) {
            //自定义异常
            CustomException ex = (CustomException) e;
            result.setCode(ex.getErrorCode());
            result.setMsg(ex.getMsg());
            log.error("抛出自定义异常");
        } else if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            result.setMsg("错误的请求");
            log.error(e.getMessage());
        } else if(e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            BindingResult bindingResult = exception.getBindingResult();

            if (bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for(FieldError fieldError: fieldErrors) {
                    result.setMsg(fieldError.getDefaultMessage());
                }
            }
            log.error(e.getMessage());
        } else {
            result.setMsg("服务器异常,请联系管理员");
            log.error(e.getMessage());
        }
        return result;
    }

}
