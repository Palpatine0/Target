package com.target.target_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Brand;

import com.target.target_common.result.BaseResult;
import com.target.target_common.service.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @DubboReference
    private BrandService brandService;

    @GetMapping("/findById")
    public BaseResult<Brand> findById(Long id){
        Brand brand = brandService.findById(id);
        return BaseResult.ok(brand);
    }

    @GetMapping("/all")
    public BaseResult<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return BaseResult.ok(brands);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody Brand brand){
        brandService.add(brand);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Brand brand){
        brandService.update(brand);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long id){
        brandService.delete(id);
        return BaseResult.ok();
    }


    @GetMapping("/search")
    public BaseResult<Page<Brand>> search(Brand brand, int page, int size){
        Page<Brand> search = brandService.search(brand, page, size);
        return BaseResult.ok(search);
    }


}
