package com.oylong.newblog.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("更新密码的vo")
@Data
public class UpdatePasswodVo {
    private String username;
    private String password;
    private String captcha;
}
