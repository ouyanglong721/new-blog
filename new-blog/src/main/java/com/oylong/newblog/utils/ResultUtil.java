package com.oylong.newblog.utils;


import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.entity.Result;

public class ResultUtil {

    public static Result buildUnSuccessResult(){
        Result result = new Result(999, "未知错误,请求失败,请联系管理员",false);
        return result;
    }

    public static Result buildSuccessResult(){
        return new Result(200, ResultCode.SUCCESS.getMessage(),true);
    }
    public static Result buildSuccessResult(String msg){
        return new Result(200, msg, true);
    }

    public static Result buildUnSuccessResult(String msg){
        return new Result(999, msg, false);
    }

    public static Result buildUnSuccessResult(int code, String msg){
        return new Result(code, msg, false);
    }

    public static Result buildResult(int code, String msg, boolean isOk){
        return new Result(code, msg, isOk);
    }
}
