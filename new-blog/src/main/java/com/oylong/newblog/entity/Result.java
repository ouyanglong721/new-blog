package com.oylong.newblog.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class Result<T> extends HashMap<String, Object> {

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    public Result() {
    }

    public void addData(String key, T value){
        super.put(key, value);
    }

    public void setData(T value){
        super.put(DATA_TAG, value);
    }

    public void setCode(int code){
        super.put(CODE_TAG, code);
    }

    public void setMsg(String msg){
        super.put(MSG_TAG, msg);
    }

}
