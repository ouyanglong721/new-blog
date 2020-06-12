package com.oylong.newblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ApiModel("文章的实体类")
@Data
public class Article {
    @TableId(type= IdType.AUTO)
    private Long id;
    @NotNull
    private Long authorId;
    private Date createTime;
    private Date modifyTime;
    @NotNull
    private String content;
    @NotNull
    private String title;
    private Long commentCount;

    @TableField(exist = false)
    private List<Category> categories;
}
