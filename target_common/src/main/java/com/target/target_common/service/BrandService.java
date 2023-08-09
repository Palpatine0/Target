package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Brand;

import java.util.List;

public interface BrandService {
    Brand findById(Long id);
    List<Brand> findAll();
    void add(Brand brand);
    void update(Brand brand);
    void delete(Long id);
    Page<Brand> search(Brand brand, int page, int size);

}
