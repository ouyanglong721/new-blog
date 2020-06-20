package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends BaseMapper<Comment> {
    IPage<Comment> selectCommentsByArticleId(IPage<Comment> page, Long articleId);
    IPage<CommentVo> selectCommentVo(IPage<CommentVo> page, @Param(Constants.WRAPPER) QueryWrapper<CommentVo> queryWrapper);
}
