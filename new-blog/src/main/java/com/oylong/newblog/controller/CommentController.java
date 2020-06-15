package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "评论有关的接口")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentService commentService;


    @ApiOperation("查询所有的评论,没有子评论")
    @GetMapping
    public Result selectAllComment(int page, int limit){
        return commentService.selectAllComment(page, limit);
    }

    @ApiOperation("查询所有的评论,有子评论")
    @GetMapping("/{articleId}")
    public Result selectCommentByArticleId(@PathVariable("articleId") Long articleId,
                                           @RequestParam("page") int page,
                                           @RequestParam("limit") int limit) {
        return commentService.selectCommentById(articleId, page, limit);
    }

    @ApiOperation("添加评论")
    @PostMapping
    public Result insertComment(@RequestBody @Valid Comment comment) {
        return commentService.insertComment(comment);
    }


    @ApiOperation("删除评论")
    @DeleteMapping
    public Result deleteComment(Long id) {
        return commentService.deleteCommentByCommentId(id);
    }

}
