package com.oylong.newblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
public class Comment {
    @TableId(type= IdType.AUTO)
    private Long id;
    @NotNull(message = "评论不能为空")
    private String content;
    @NotNull(message = "评论文章不能为空")
    private Long articleId;
    private Date createTime;
    private Long parentId;
    private Long toId;
    @NotNull(message = "评论昵称不能为空")
    private String nickname;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "个人网站不能为空")
    private String website;

    @TableField(exist = false)
    private List<Comment> children;
}
