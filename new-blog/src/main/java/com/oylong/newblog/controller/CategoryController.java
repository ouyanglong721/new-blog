package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Category;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "分类相关的接口")
@RequestMapping("/categories")
public class CategoryController {
    @Resource
    CategoryService categoryService;


    @ApiOperation("查询树状菜单")
    @GetMapping("/tree")
    public Result getTree(){
        return categoryService.selectCategoryByTree();
    }

    @ApiOperation("添加分类")
    @PostMapping
    public Result addCategory(@RequestBody @Valid Category category){
        return categoryService.addCategory(category);
    }

    @ApiOperation("删除分类")
    @GetMapping("/{id}")
    public Result addCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategoryById(id);
    }

    @ApiOperation("修改分类")
    @PutMapping
    public Result updateCategory(@RequestBody @Valid Category category){
        return categoryService.updateCategory(category);
    }

    @ApiOperation("获取所有分类")
    @GetMapping
    public Result getAllCategories(){
        return categoryService.selectCategories();
    }

}
