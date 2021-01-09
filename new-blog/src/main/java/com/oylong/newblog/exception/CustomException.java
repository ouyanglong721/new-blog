package com.oylong.newblog.exception;


import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.entity.Result;
import lombok.Data;


@Data
public class CustomException extends RuntimeException {
    protected int errorCode;
    protected String msg;
    protected String[] errorMessageArguments;
    protected Result result;


    public CustomException(ResultCode code){
        this.errorCode = code.getCode();
        this.msg = ResultCode.getMessageByCode(code.getCode());
    }

    public CustomException(String msg){
        this.errorCode = 999;
        this.msg = msg;
    }
}
