package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Specification;
import com.target.target_common.pojo.SpecificationOptions;

import java.util.List;

public interface SpecificationService {
    void add(Specification specification);
    void update(Specification specification);
    void delete(Long[] ids);
    Specification findById(Long id);
    Page<Specification> search(int page, int size);
    List<Specification> findByProductTypeId(Long id);
    //
    void addOption(SpecificationOptions specificationOptions);
    void deleteOption(Long[] ids);
}
