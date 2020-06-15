package com.oylong.newblog.service;

import com.oylong.newblog.entity.Comment;
import com.oylong.newblog.entity.Result;

public interface CommentService {
    Result insertComment(Comment comment);
    Result deleteCommentByCommentId(Long id);
    Result selectAllComment(int page, int limit);
    Result selectCommentById(Long articleId, int page, int limit);
}
