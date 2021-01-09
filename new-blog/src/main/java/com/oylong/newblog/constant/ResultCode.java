package com.oylong.newblog.constant;

public enum ResultCode {
    /**
     * @Author: oyLong
     * @Description: 返回的信息代码以及code的对应
     * @Date: 16:36 2020/4/5
     */


    /* 成功 */
    SUCCESS(200, "成功"),
    LOGIN_SUCCESS(201, "登入成功"),

    /* 默认失败 包含系统错误*/
    COMMON_FAIL(999, "失败"),

    //以下是可以给用户提示的错误
    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 2000-2099*/
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /*token相关*/
    TOKEN_NOT_EXIST(2100, "登入状态有误，请重新登入"),


    /* 业务错误*/
    NO_PERMISSION(3001, "没有权限"),
    CACHE_ERROR(3002, "缓存出现错误"),
    RESULT_IS_NOT_EXIST(3003,"查询的结果不存在");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
