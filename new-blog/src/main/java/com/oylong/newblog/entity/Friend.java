package com.oylong.newblog.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Friend {
    @TableId(type= IdType.AUTO)
    private Long id;
    @NotBlank(message = "友链名不能为空")
    private String name;
    @NotBlank(message = "友链网址不能为空")
    private String website;
    private String iconUrl;
}
