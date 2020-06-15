package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.dao.CommentMapper;
import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.CommentService;
import com.oylong.newblog.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;


    @Override
    public Result insertComment(Comment comment) {
        comment.setCreateTime(new Date());
        if(comment.getParentId() == 0) {
            comment.setParentId(null);
        }
        commentMapper.insert(comment);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteCommentByCommentId(Long id) {
        Comment comment = commentMapper.selectById(id);
        if(comment == null) {
            return ResultUtil.buildUnSuccessResult("评论不存在");
        }
        else {
            commentMapper.deleteById(id);
            return ResultUtil.buildSuccessResult();
        }
    }

    @Override
    public Result selectAllComment(int page, int limit) {
        IPage<Comment> iPage = new Page(page, limit);

        IPage tPage = commentMapper.selectPage(iPage, null);

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
}
