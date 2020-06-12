package com.oylong.newblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpConfig {
    /**
     * @Author: oyLong
     * @Description: mp的分页插件
     * @Date: 11:49 2020/6/10
     * @param
     * @Return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
    */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
