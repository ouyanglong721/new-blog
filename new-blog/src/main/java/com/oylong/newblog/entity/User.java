package com.oylong.newblog.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {
    @TableId(type= IdType.AUTO)
    private Long id;
    @NotNull(message = "用户名不能为空")
    private String username;
    private String password;
    private Date createTime;
    @NotNull(message = "昵称不能为空")
    private String nickname;
    @Email(message = "邮箱格式不正确")
    @NotNull(message = "邮箱不能为空")
    private String email;
    private String avatarUrl;
}
