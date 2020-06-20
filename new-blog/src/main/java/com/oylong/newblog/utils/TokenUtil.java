package com.oylong.newblog.utils;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class TokenUtil {

    @Resource
    private RedisUtil redisUtil;


    public  String generateToken(){
        return IdUtil.simpleUUID();
    }

    public  void saveToken(String token, String username){
        saveToken(token, username, 3600*3);
        //默认三小时
    }

    public  void saveToken(String token, String username, long time){
        redisUtil.set("token:"+token, username, time);
    }

    public  boolean isTokenAvalible(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        return redisUtil.get("token:"+token) != null;
    }

    public  String getUsernameByToken(String token){
            return (String) redisUtil.get("token:"+token);
    }

    public  void deleteToken(String token){
        redisUtil.del("token:"+token);
    }

}
