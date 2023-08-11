package com.target.target_user_category_api.controller;


import com.target.target_common.pojo.Category;
import com.target.target_common.result.BaseResult;
import com.target.target_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
public class CategoryController {
    @DubboReference
    private CategoryService categoryService;

    @GetMapping("/all")
    public BaseResult<List<Category>> findAll(){
        List<Category> categories = categoryService.findAll();
        return BaseResult.ok(categories);
    }
}
