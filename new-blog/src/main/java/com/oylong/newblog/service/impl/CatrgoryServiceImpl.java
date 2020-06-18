package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.dao.CategoryMapper;
import com.oylong.newblog.entity.Category;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.CategoryService;
import com.oylong.newblog.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Category c = categoryMapper.selectById(category.getId());
        if(c == null){
            return ResultUtil.buildUnSuccessResult("分类不存在");
        }
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
            return ResultUtil.buildUnSuccessResult("此分类不存在");
        }
        categoryMapper.deleteById(id);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selctCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        if(category == null){
            return ResultUtil.buildUnSuccessResult("此分类不存在");
        }
        Result result = ResultUtil.buildSuccessResult();
        result.setData(category);

        return result;
    }

    @Override
    public Result selectChildCategories(Long id, int page, int limit) {
        if(id == -1){
            id = null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        if(id != null){
            queryWrapper.eq("parent_id", id);
        } else {
            queryWrapper.isNull("parent_id");
        }
        IPage iPage = new Page(page, limit);
        IPage iPage1 = categoryMapper.selectCategoryVo(iPage, queryWrapper);
        Result result = ResultUtil.buildSuccessResult();
        Map<String, Object> map = new HashMap<>();
        map.put("total", iPage1.getTotal());
        map.put("list", iPage1.getRecords());
        result.setData(map);
        return result;
    }
}
