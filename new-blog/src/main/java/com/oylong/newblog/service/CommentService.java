package com.oylong.newblog.service;

import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;

import java.util.List;

public interface CommentService {
    Result insertComment(Comment comment);
    Result deleteCommentByCommentId(Long id);
    Result selectAllComment(Long articleId, String content, int page, int limit);
    Result selectCommentById(Long articleId, int page, int limit);
    Result deleteList(List<Long> deleteList);
}
