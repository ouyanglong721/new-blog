package com.oylong.newblog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private Long id;

    private String content;
    private Long articleId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Long parentId;
    private String title;

    private String nickname;
    private String email;
    private String website;
}
