package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);
    void update(Category category);
    void updateStatus(Long id, Integer status);
    void delete(Long[] ids);
    Category findById(Long id);
    Page<Category> search(int page, int size);
    List<Category> findAll();
}
