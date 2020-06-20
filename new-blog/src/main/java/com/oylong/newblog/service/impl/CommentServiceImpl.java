package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.dao.ArticleMapper;
import com.oylong.newblog.dao.CommentMapper;
import com.oylong.newblog.entity.Article;
import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.CommentService;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.vo.CommentVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    ArticleMapper articleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertComment(Comment comment) {
        comment.setCreateTime(new Date());
        if(comment.getParentId() == 0) {
            comment.setParentId(null);
        }

        commentMapper.insert(comment);
        Article article = articleMapper.selectById(comment.getArticleId());
        article.setCommentCount(article.getCommentCount()+1);
        articleMapper.updateById(article);

        return ResultUtil.buildSuccessResult();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteCommentByCommentId(Long id) {
        deleteCommentWithArticle(id);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selectAllComment(Long articleId,String content, int page, int limit) {
        IPage<CommentVo> iPage = new Page(page, limit);

        QueryWrapper queryWrapper = new QueryWrapper();

        if(!StringUtils.isEmpty(content)) {
            queryWrapper.like("comment.content", content);
        }

        if(articleId != null){
            queryWrapper.eq("comment.article_id", articleId);
        }

        IPage tPage = commentMapper.selectCommentVo(iPage, queryWrapper);

        Result result = ResultUtil.buildSuccessResult();
        Map<String, Object> map = new HashMap<>();
        map.put("total", tPage.getTotal());
        map.put("list", tPage.getRecords());
        result.setData(map);
        return result;
    }

    @Override
    public Result selectCommentById(Long articleId, int page, int limit) {
        IPage<Comment> page1 = new Page(page, limit);

        IPage<Comment> commentIPage = commentMapper.selectCommentsByArticleId(page1, articleId);

        Map<String, Object> map = new HashMap();
        map.put("total", commentIPage.getTotal());
        map.put("list", commentIPage.getRecords());
        Result result = ResultUtil.buildSuccessResult();
        result.setData(map);
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteList(List<Long> deleteList) {
//        commentMapper.deleteBatchIds(deleteList);
        for(Long id: deleteList){
            deleteCommentWithArticle(id);
        }
        return ResultUtil.buildSuccessResult();
    }


    private void deleteCommentWithArticle(Long id){
        Comment comment = commentMapper.selectById(id);
        if(comment == null) {
            throw new CustomException(ResultCode.RESULT_IS_NOT_EXIST);
        }
        commentMapper.deleteById(id);

        Article article = articleMapper.selectById(comment.getArticleId());
        article.setCommentCount(article.getCommentCount()-1);
        articleMapper.updateById(article);
    }
}
