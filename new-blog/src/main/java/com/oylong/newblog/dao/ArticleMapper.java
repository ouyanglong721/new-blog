package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oylong.newblog.entity.Article;
import com.oylong.newblog.vo.SimpleArticleVo;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper extends BaseMapper<Article> {
    IPage<Article> selectMyPage(IPage<Article> page, @Param(Constants.WRAPPER) QueryWrapper<Article> queryWrapper);
    Article selectArticleById(Long id);
    IPage<SimpleArticleVo> selectSimpleArticle(IPage<SimpleArticleVo> page, @Param(Constants.WRAPPER) QueryWrapper<SimpleArticleVo> queryWrapper);
}
