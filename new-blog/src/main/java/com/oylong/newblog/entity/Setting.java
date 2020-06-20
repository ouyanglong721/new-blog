package com.oylong.newblog.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Setting {
    @NotNull(message = "参数不正确")
    private Long id;
    @NotBlank(message = "网站名不能为空")
    private String name;
    private String description;
    @NotBlank(message = "网站名url不能为空")
    private String url;
    private String providerNum;

    @NotNull(message = "请确认是否开启评论")
    private Boolean commentEnabled;
}
