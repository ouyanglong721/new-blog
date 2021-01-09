package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.dao.ArticleCategoryMapper;
import com.oylong.newblog.dao.ArticleMapper;
import com.oylong.newblog.entity.Article;
import com.oylong.newblog.entity.ArticleCategory;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.ArticleService;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.vo.SimpleArticleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: oyLong
 * @Description:  文章的业务实现类
 * @Date: 13:25 2020/7/11
*/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addArticle(Article article) {
        article.setCreateTime(new Date());
        article.setModifyTime(new Date());
        article.setCommentCount(0);
        articleMapper.insert(article);
        updateCategory(article.getId(), article.getCategories(),  new ArrayList<>());
        return ResultUtil.buildSuccessResult();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateArticle(Article article) {
        if(article.getId() == null){
            throw new CustomException(ResultCode.PARAM_NOT_VALID);
        }

        Article tarticle = articleMapper.selectArticleById(article.getId());
        if(tarticle == null){
            return ResultUtil.buildUnSuccessResult("文章不存在");
        }
        article.setCommentCount(null);
        article.setAuthorId(null);
        article.setModifyTime(new Date());


        updateCategory(article.getId(), article.getCategories(), tarticle.getCategories());

        articleMapper.updateById(article);

        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if(article == null) {
            return ResultUtil.buildUnSuccessResult("文章不存在");
        }
        articleMapper.deleteById(id);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result findAllArticles(int page, int limit) {
        IPage<Article> iPage = new Page<>(page, limit);
        IPage ipage = articleMapper.selectMyPage(iPage, null);
        return getPageResult(ipage);
    }

    @Override
    public Result findArticleByAuthorId(Long authorId,int page, int limit) {
        IPage<Article> iPage = new Page<>(page, limit);
        QueryWrapper wrapper = new QueryWrapper<>().eq("author_id", authorId);
        IPage ipage = articleMapper.selectMyPage(iPage, wrapper);
        return getPageResult(ipage);
    }

    @Override
    public Result selectSimpleArticles(String title,Boolean isAdmin, int page, int limit) {
        IPage<SimpleArticleVo> iPage = new Page<>(page, limit);
        IPage<SimpleArticleVo> simpleArticleVoIPage;
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!isAdmin){
            queryWrapper.eq("state", 1);
        }
        if(StringUtils.isEmpty(title)){
            simpleArticleVoIPage = articleMapper.selectSimpleArticle(iPage, queryWrapper);
        }else {
            queryWrapper.like("article.title", title);
            simpleArticleVoIPage = articleMapper.selectSimpleArticle(iPage, queryWrapper);
        }
        for (SimpleArticleVo record : simpleArticleVoIPage.getRecords()) {
            record.setContent(record.getContent()+"...");
        }
        return getPageResult(simpleArticleVoIPage);
    }

    @Override
    public Result updateArticleState(Article article) {
        if(article.getId() == null){
            throw new CustomException(ResultCode.PARAM_NOT_VALID);
        }

        Article tarticle = articleMapper.selectArticleById(article.getId());
        if(tarticle == null){
            return ResultUtil.buildUnSuccessResult("文章不存在");
        }

        articleMapper.updateById(article);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selectArticleById(Long id) {
        Article article = articleMapper.selectArticleById(id);
        if(article == null){
            ResultUtil.buildUnSuccessResult("文章不存在");
        }
        Result result = ResultUtil.buildSuccessResult();
        result.setData(article);
        return result;
    }


    private Result getPageResult(IPage ipage) {
        Result result = ResultUtil.buildSuccessResult();
        result.addData("total", ipage.getTotal());
        result.addData("list", ipage.getRecords());
        return result;
    }

    private  void updateCategory(Long articleId,List<Long> newCategories, List<Long> oldCategories){

        //处理需要被变动的标签
        Set<Long> set_new = new HashSet<>(newCategories);
        Set<Long> set_old = new HashSet<>(oldCategories);


        //结果的标签
        Set<Long> setAdd = new HashSet<>();
        Set<Long> setDelete = new HashSet<>();

        Map deleteMap = new HashMap();

        /**
         * @Author: oyLong
         * @Description:  新的集合有老的集合没有的  需要add
         * @Date: 15:00 2020/6/11
         */
        for (Long aLong : set_new) {
            if (!set_old.contains(aLong)) {
                setAdd.add(aLong);
            }
        }

        /**
         * @Author: oyLong
         * @Description: 老的集合有 新的集合没有的 需要delete
         * @Date: 15:00 2020/6/11
         */
        for (Long aLong : set_old) {
            if(!set_new.contains(aLong)){
                setDelete.add(aLong);
            }
        }


        for (Long aLong : setDelete) {
            deleteMap.clear();
            deleteMap.put("article_id", articleId);
            deleteMap.put("category_id", aLong);
            articleCategoryMapper.deleteByMap(deleteMap);
        }

        for (Long aLong : setAdd) {
            ArticleCategory articleCategory = new ArticleCategory(null,articleId, aLong);
            articleCategoryMapper.insert(articleCategory);
        }
    }
}
