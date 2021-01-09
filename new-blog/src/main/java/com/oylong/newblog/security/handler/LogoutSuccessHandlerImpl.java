package com.oylong.newblog.security.handler;

import com.alibaba.fastjson.JSON;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: new-blog
 * @Description:
 * @Author: OyLong
 * @Date: 2020/8/13 14:12
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = tokenUtil.getUsernameByToken(request.getHeader("Authorization"));
        if (StringUtils.isNoneBlank(token)) {
            // 删除用户缓存记录
            tokenUtil.deleteToken(token);
        }
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(ResultUtil.buildSuccessResult("退出成功")));
    }
}
