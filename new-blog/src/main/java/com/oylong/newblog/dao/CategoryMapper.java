package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oylong.newblog.entity.Category;
import com.oylong.newblog.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category>  selectCategoryByTree();
    IPage<CategoryVo> selectCategoryVo(IPage<CategoryVo> page, @Param(Constants.WRAPPER) QueryWrapper<CategoryVo> queryWrapper);
}
