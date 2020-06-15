package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.dao.ArticleCategoryMapper;
import com.oylong.newblog.dao.ArticleMapper;
import com.oylong.newblog.entity.Article;
import com.oylong.newblog.entity.ArticleCategory;
import com.oylong.newblog.entity.Category;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.ArticleService;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.vo.SimpleArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;


    @Override
    public Result addArticle(Article article) {
        article.setCreateTime(new Date());
        article.setModifyTime(new Date());
        article.setCommentCount(0L);
        articleMapper.insert(article);
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
        article.setCreateTime(null);
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
    public Result selectSimpleArticles(int page, int limit) {
        IPage<SimpleArticleVo> iPage = new Page<>(page, limit);
        IPage<SimpleArticleVo> simpleArticleVoIPage = articleMapper.selectSimpleArticle(iPage);
        for (SimpleArticleVo record : simpleArticleVoIPage.getRecords()) {
            record.setContent(record.getContent()+"...");
        }
        return getPageResult(simpleArticleVoIPage);
    }


    private Result getPageResult(IPage ipage) {
        Map<String, Object> res = new HashMap<>();
        res.put("total",ipage.getTotal());
        res.put("list", ipage.getRecords());
        Result result = ResultUtil.buildSuccessResult();
        result.setData(res);
        return result;
    }

    private  void updateCategory(Long articleId,List<Category> newCategories, List<Category> oldCategories){

        //处理需要被变动的标签
        Set<Long> set_new = new HashSet<>();
        Set<Long> set_old = new HashSet<>();

        for (Category category : newCategories) {
            set_new.add(category.getId());
        }
        for (Category category : oldCategories) {
            set_old.add(category.getId());
        }
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
