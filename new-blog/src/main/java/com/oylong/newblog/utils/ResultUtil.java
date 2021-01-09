package com.oylong.newblog.utils;


import com.oylong.newblog.entity.Result;

import static com.oylong.newblog.entity.Result.*;

public class ResultUtil {


    public static Result buildUnSuccessResult(){
        Result result = new Result();
        result.addData(CODE_TAG, 999);
        result.addData(MSG_TAG, "未知错误,请求失败,请联系管理员");

        return result;
    }

    public static Result buildSuccessResult(){
        Result result = new Result();
        result.addData(CODE_TAG, 200);
        result.addData(MSG_TAG, "操作成功");
        return result;
    }
    public static Result buildSuccessResult(String msg){
        Result result = new Result();
        result.addData(CODE_TAG, 200);
        result.addData(MSG_TAG, msg);
        return result;
    }

    public static Result buildUnSuccessResult(String msg){
        Result result = new Result();
        result.addData(CODE_TAG, -1);
        result.addData(MSG_TAG, msg);
        return result;
    }

    public static Result buildUnSuccessResult(int code, String msg){
        Result result = new Result();
        result.addData(CODE_TAG, code);
        result.addData(MSG_TAG, msg);
        return result;
    }

    public static Result buildResult(int code, String msg){
        Result result = new Result();
        result.addData(CODE_TAG, code);
        result.addData(MSG_TAG, msg);
        return result;
    }
}
