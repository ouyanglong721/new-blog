package com.oylong.newblog.controller;

import com.oylong.newblog.annotation.AdminPermission;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.CommentService;
import com.oylong.newblog.service.SettingService;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "评论有关的接口")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private SettingService settingService;

    @Resource
    private TokenUtil tokenUtil;


    @ApiOperation("查询所有的评论")
    @GetMapping
    public Result selectAllComment(@RequestParam(name = "content", required = false) String content,
                                   @RequestParam(name = "articleId", required = false) Long articleId,
                                   @RequestParam("page") int page,
                                   @RequestParam("limit") int limit) {
        if(articleId == -1){
            articleId = null;
        }
        return commentService.selectAllComment(articleId, content, page, limit);
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
    @AdminPermission(validate = false)
    public Result insertComment(@RequestHeader("token") String token,@RequestBody @Valid Comment comment) {
        if (settingService.commentEnabled() || tokenUtil.isTokenAvalible(token)) {
            return commentService.insertComment(comment);
        }
        return ResultUtil.buildUnSuccessResult("评论失败,请联系管理员开启评论权限");
    }


    @ApiOperation("删除评论")
    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable("id") Long id) {
        return commentService.deleteCommentByCommentId(id);
    }

    @ApiOperation("批量删除评论")
    @DeleteMapping
    public Result deleteComment(@RequestBody String deleteList) {
        return commentService.deleteList(getListByString(deleteList));
    }

    private List<Long> getListByString(String listStr){
        if(StringUtils.isEmpty(listStr) || !(listStr.startsWith("[") && listStr.endsWith("]"))){
            throw new CustomException(ResultCode.PARAM_TYPE_ERROR);
        }

        listStr = listStr.substring(1,listStr.length()-1);
        String[] split = listStr.split(",");

        List<Long> list = new ArrayList<>();

        for(String str: split){
            list.add(Long.parseLong(str));
        }

        return list;
    }

}
