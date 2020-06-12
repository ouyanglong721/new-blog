package com.oylong.newblog.service.impl;

import com.oylong.newblog.dao.CategoryMapper;
import com.oylong.newblog.entity.Category;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.CategoryService;
import com.oylong.newblog.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CatrgoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Result selectCategoryByTree() {
        List<Category> categories = categoryMapper.selectCategoryByTree();
        Result result = ResultUtil.buildSuccessResult();
        result.setData(categories);
        return result;
    }

    @Override
    public Result addCategory(Category category) {
        if(StringUtils.isEmpty(category.getName())){
            return ResultUtil.buildUnSuccessResult("标签名不能为空");
        }
        categoryMapper.insert(category);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result updateCategory(Category category) {
        categoryMapper.updateById(category);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selectCategories() {
        List<Category> categories = categoryMapper.selectList(null);
        Result result = ResultUtil.buildSuccessResult();
        result.setData(categories);
        return result;
    }


    @Override
    public Result deleteCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        if(category == null) {
            return ResultUtil.buildUnSuccessResult("此标签不存在");
        }
        categoryMapper.deleteById(id);
        return ResultUtil.buildSuccessResult();
    }
}
