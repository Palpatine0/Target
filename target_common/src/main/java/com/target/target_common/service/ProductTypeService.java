package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    void add(ProductType productType);
    void update(ProductType productType);
    ProductType findById(Long id);
    void delete(Long id);
    Page<ProductType> search(ProductType productType, int page, int size);
    List<ProductType> findProductType(ProductType productType);
}
