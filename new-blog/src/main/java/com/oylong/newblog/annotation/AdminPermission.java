package com.oylong.newblog.annotation;

import java.lang.annotation.*;


/**
 * @Author: oyLong
 * @Description:
 * 这是一个用于权限验证的,未使用此注解默认权限检测
 * 在所有不需要管理员权限的操作上使用此注解
 * 并且指定validate为false
 * @Date: 14:23 2020/4/5
 * @Param:
 * @Return:
*/
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminPermission {
    boolean validate() default true;
}
