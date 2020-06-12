package com.oylong.newblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategory {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long articleId;
    private Long categoryId;
}
