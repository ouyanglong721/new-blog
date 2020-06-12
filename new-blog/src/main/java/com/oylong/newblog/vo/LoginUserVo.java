package com.oylong.newblog.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("登入用户")
public class LoginUserVo {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "验证码不能为空")
    private String captcha;
    @NotNull(message = "验证码不能为空")
    private String cid;
    private String rememberMe;
}
