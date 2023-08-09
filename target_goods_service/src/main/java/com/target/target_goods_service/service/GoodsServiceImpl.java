package com.target.target_goods_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.*;

import com.target.target_common.service.GoodsService;
import com.target.target_goods_service.mapper.GoodsImagesMapper;
import com.target.target_goods_service.mapper.GoodsMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImagesMapper goodsImageMapper;

    @Override
    public void add(Goods goods) {
        goodsMapper.insert(goods);
        Long goodsId = goods.getId();

        //add subs:image
        List<GoodsImage> images = goods.getImages();
        for (GoodsImage image : images) {
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }

        //add subs:specifications
        List<Specification> specifications = goods.getSpecifications();
        List<SpecificationOption> options = new ArrayList();
        for (Specification specification : specifications) {
            options.addAll(specification.getSpecificationOptions());
        }
        for (SpecificationOption option : options) {
            goodsMapper.addGoodsSpecificationOption(goodsId, option.getId());
        }
    }

    @Override
    public void update(Goods goods) {
        Long goodsId = goods.getId();
        //1.delete original
        QueryWrapper<GoodsImage> queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsId", goodsId);
        goodsImageMapper.delete(queryWrapper);
        goodsMapper.deleteGoodsSpecificationOption(goodsId);

        //2.update
        goodsMapper.updateById(goods);

        //add subs:image
        List<GoodsImage> images = goods.getImages();
        for (GoodsImage image : images) {
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }
        //add subs:specifications
        List<Specification> specifications = goods.getSpecifications();
        List<SpecificationOption> options = new ArrayList();
        for (Specification specification : specifications) {
            options.addAll(specification.getSpecificationOptions());
        }
        for (SpecificationOption option : options) {
            goodsMapper.addGoodsSpecificationOption(goodsId, option.getId());
        }
    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {

    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        if (goods != null && StringUtils.hasText(goods.getGoodsName())) {
            queryWrapper.like("goodsName", goods.getGoodsName());
        }
        Page pagee = goodsMapper.selectPage(new Page(page, size), queryWrapper);
        return pagee;
    }

    @Override
    public List<GoodsDesc> findAll() {
        return null;
    }

    @Override
    public GoodsDesc findDesc(Long id) {
        return null;
    }
}
