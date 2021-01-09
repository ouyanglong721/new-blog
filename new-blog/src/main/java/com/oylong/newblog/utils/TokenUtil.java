package com.oylong.newblog.utils;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class TokenUtil {

    public static final String LOGIN_TOKEN_HEADER =  "LOGIN_TOKEN:";

    @Resource
    private RedisUtil redisUtil;


    public  String generateToken(){
        return IdUtil.simpleUUID();
    }

    public  void saveToken(String token, String username){
        saveToken(token, username, 3600*12);
        //默认三小时
    }

    public  void saveToken(String token, String username, long time){
        redisUtil.set(LOGIN_TOKEN_HEADER+token, username, time);
    }

    public  boolean isTokenAvalible(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        return redisUtil.get(LOGIN_TOKEN_HEADER+token) != null;
    }

    public  String getUsernameByToken(String token){
            return (String) redisUtil.get(LOGIN_TOKEN_HEADER+token);
    }

    public  void deleteToken(String token){
        redisUtil.del(LOGIN_TOKEN_HEADER+token);
    }

}
