package com.oylong.newblog.controller;

import com.oylong.newblog.annotation.AdminPermission;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "主页的接口")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    ArticleService articleService;

    @AdminPermission(validate = false)
    @GetMapping("/articles")
    public Result getSimpleArticles(@RequestParam("page") int page, @RequestParam("limit")int limit){
        return articleService.selectSimpleArticles(page, limit);
    }

}
