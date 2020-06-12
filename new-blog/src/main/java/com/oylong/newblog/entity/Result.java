package com.oylong.newblog.entity;

import lombok.Data;

@Data
public class Result<T>{
    boolean isOk;
    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, boolean isOk) {
        this();
        this.code = code;
        this.isOk = isOk;
    }

    public Result(int code, String msg, boolean isOk) {
        this(code, isOk);
        this.msg = msg;
    }
}
