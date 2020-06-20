package com.oylong.newblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ApiModel("文章的实体类")
@Data
public class Article {
    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    @NotNull(message = "作者不能为空")
    private Long authorId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @NotBlank(message = "文章内容不能为空")
    private String content;
    @NotBlank(message = "标题不能为空")
    private String title;
    private Integer commentCount;
    private Boolean state;   //true 表示公开  false表示私有
    private Boolean isDraft;

    @TableField(exist = false)
    private List<Long> categories;
}
