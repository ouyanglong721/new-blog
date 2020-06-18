package com.oylong.newblog.controller;

import com.oylong.newblog.annotation.AdminPermission;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.ArticleService;
import com.oylong.newblog.utils.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "主页的接口")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    ArticleService articleService;

    @AdminPermission(validate = false)
    @GetMapping("/articles")
    public Result getSimpleArticles(@RequestParam(name = "title",required = false)String title, @RequestHeader("token")String token, @RequestParam("page") int page, @RequestParam("limit")int limit){
        if(TokenUtil.isTokenAvalible(token)){
            return articleService.selectSimpleArticles(title, true, page, limit);
        }
        return articleService.selectSimpleArticles(title, false, page, limit);
    }

}
