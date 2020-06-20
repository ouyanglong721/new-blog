package com.oylong.newblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@MapperScan("com.oylong.newblog.dao")
public class NewBlogApplication{

    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {
        NewBlogApplication.ac = SpringApplication.run(NewBlogApplication.class, args);
    }

}
