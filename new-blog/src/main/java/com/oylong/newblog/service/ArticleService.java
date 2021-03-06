package com.oylong.newblog.service;

import com.oylong.newblog.entity.Article;
import com.oylong.newblog.entity.Result;

public interface ArticleService {
    Result addArticle(Article article);
    Result findAllArticles(int page, int limit);
    Result updateArticle(Article article);
    Result deleteArticleById(Long id);
    Result findArticleByAuthorId(Long authorId, int page, int limit);
    Result selectSimpleArticles(String title,Boolean isAdmin,int page, int limit);

    Result updateArticleState(Article article);

    Result selectArticleById(Long id);
}
