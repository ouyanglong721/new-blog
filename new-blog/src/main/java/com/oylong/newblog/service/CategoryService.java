package com.oylong.newblog.service;

import com.oylong.newblog.entity.Category;
import com.oylong.newblog.entity.Result;

public interface CategoryService {
    Result selectCategoryByTree();
    Result selectCategories();
    Result addCategory(Category category);
    Result updateCategory(Category category);
    Result deleteCategoryById(Long id);
    Result selctCategoryById(Long id);

    Result selectChildCategories(Long id, int page, int limit);
}
