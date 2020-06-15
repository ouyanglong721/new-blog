package com.oylong.newblog.interceptor;

import com.oylong.newblog.annotation.AdminPermission;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: oyLong
 * @Description: 权限验证的拦截器,并且通过注解来判断
 * @Date: 14:29 2020/4/5
 * @Param:
 * @Return:
*/

@Slf4j
@Component
public class PermissonHandlerIntercepor extends HandlerInterceptorAdapter {
    /**
     * @Author: oyLong
     * @Description: 前值判断
     * @Date: 14:30 2020/4/5
     * @Param: [request, response, handler]
     * @Return: boolean
    */
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "*");

        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            /*
            * 判断这个是不是一个方法的拦截器
            **/
            HandlerMethod handlerMethod = (HandlerMethod)handler;

            AdminPermission adminPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AdminPermission.class);
            System.out.println(adminPermission);
            if(adminPermission != null && !adminPermission.validate()) {
                /*
                * 判断这个方法的类是否有注解
                *  */
                return super.preHandle(request, response, handler);
            }
            adminPermission = handlerMethod.getMethod().getAnnotation(AdminPermission.class);
            if(adminPermission != null && !adminPermission.validate()) {
                /*
                 * 判断这个方法是否有注解
                 *  */
                return super.preHandle(request, response, handler);
            }

            //测试用的
//            if(true){
//                return true;
//            }
            /*
            * 如果没有使用注解,进行验证权限
            * **/
            String token = request.getHeader("token");

            if(StringUtils.isEmpty(token)) {
                throw new CustomException(ResultCode.NO_PERMISSION);
            }

            if(redisUtil.get("token:"+token) == null) {
                throw new CustomException(ResultCode.NO_PERMISSION);
            }
            //通过,刷新时间
            redisUtil.expire(token, 3600*3);
            return true;
        }
        return true;
    }
}
