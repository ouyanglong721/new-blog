package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oylong.newblog.entity.Comment;

public interface CommentMapper extends BaseMapper<Comment> {
    IPage<Comment> selectCommentsByArticleId(IPage<Comment> page, Long articleId);
}
