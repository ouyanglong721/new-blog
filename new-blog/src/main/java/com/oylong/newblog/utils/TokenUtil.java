package com.oylong.newblog.utils;

import cn.hutool.core.util.IdUtil;
import com.oylong.newblog.NewBlogApplication;
import org.apache.commons.lang3.StringUtils;

public class TokenUtil {

    private static RedisUtil redisUtil = (RedisUtil) NewBlogApplication.ac.getBean("redisUtil");



    public static String generateToken(){
        return IdUtil.simpleUUID();
    }

    public static void saveToken(String token, String username){
        saveToken(token, username, 3600*3);
        //默认三小时
    }

    public static void saveToken(String token, String username, long time){
        redisUtil.set("token:"+token, username, time);
    }

    public static boolean isTokenAvalible(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        return redisUtil.get("token:"+token) != null;
    }

    public static String getUsernameByToken(String token){
            return (String) redisUtil.get("token:"+token);
    }

    public static void deleteToken(String token){
        redisUtil.del("token:"+token);
    }

}
