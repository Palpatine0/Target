package com.target.target_goods_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.exception.BusException;
import com.target.target_common.pojo.ProductType;

import com.target.target_common.result.CodeEnum;
import com.target.target_common.service.ProductTypeService;
import com.target.target_goods_service.mapper.ProductTypeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@DubboService
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public void add(ProductType productType) {
        Long parentId = productType.getParentId();
        ProductType productTypeParent = productTypeMapper.selectById(parentId);
        //set level before insert
        if (productTypeParent == null) {
            productType.setLevel(1);
        } else if (productTypeParent.getLevel() < 3) {
            productType.setLevel(productTypeParent.getLevel() + 1);
        } else if (productTypeParent.getLevel() >= 3) {
            throw new BusException(CodeEnum.INSERT_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.insert(productType);
    }

    @Override
    public void update(ProductType productType) {
        Long parentId = productType.getParentId();
        ProductType productTypeParent = productTypeMapper.selectById(parentId);
        //set level before update
        if (productTypeParent == null) {
            productType.setLevel(1);
        } else if (productTypeParent.getLevel() < 3) {
            productType.setLevel(productTypeParent.getLevel() + 1);
        } else if (productTypeParent.getLevel() >= 3) {
            throw new BusException(CodeEnum.INSERT_PRODUCT_TYPE_ERROR);
        }

        productTypeMapper.updateById(productType);
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeMapper.selectById(id);
    }

    @Override
    public void delete(Long id) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper();
        //if any record in table own its parentId means this record was its sub
        queryWrapper.eq("parentId", id);
        List<ProductType> productTypes = productTypeMapper.selectList(queryWrapper);
        //prevent delete if own sub type
        if (productTypes != null && productTypes.size() > 0) {
            throw new BusException(CodeEnum.DELETE_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.deleteById(id);
    }

    @Override
    public Page<ProductType> search(ProductType productType, int page, int size) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper();
        if (productType != null) {
            if (StringUtils.hasText(productType.getName())) {
                queryWrapper.like("name", productType.getName());
            }
            //get its subs
            if (productType.getParentId() != null) {
                queryWrapper.eq("parentId", productType.getParentId());
            }
        }
        return productTypeMapper.selectPage(new Page(page,size),queryWrapper);
    }

    @Override
    public List<ProductType> findProductType(ProductType productType) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper();
        if (productType != null) {

            if (StringUtils.hasText(productType.getName())) {
                queryWrapper.like("name", productType.getName());
            }

            if (productType.getParentId() != null) {
                queryWrapper.eq("parentId", productType.getParentId());
            }
        }
        return productTypeMapper.selectList(queryWrapper);
    }
}
