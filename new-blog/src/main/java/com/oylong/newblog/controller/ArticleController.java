package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Article;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "文章相关的接口")
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Resource
    ArticleService articleService;




    @ApiOperation("添加文章")
    @PostMapping
    public Result addArticle(@Valid @RequestBody Article article){
        return articleService.addArticle(article);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/{id}")
    public Result deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticleById(id);
    }

    @ApiOperation("查询文章")
    @GetMapping
    public Result getArticle(@RequestParam("page")int page, @RequestParam("limit") int limit){
        return articleService.findAllArticles(page, limit);
    }

    @GetMapping("/test")
    public Result test(@RequestParam("page")int page, @RequestParam("limit") int limit){
        return articleService.findArticleByAuthorId(1L,page, limit);
    }

    @ApiOperation(("修改文章"))
    @PutMapping
    public Result updateArticle(@RequestBody @Valid Article article){
        return articleService.updateArticle(article);
    }
}
