package com.oylong.newblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oylong.newblog.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category>  selectCategoryByTree();
}
