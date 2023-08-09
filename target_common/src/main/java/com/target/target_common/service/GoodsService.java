package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.Goods;
import com.target.target_common.pojo.GoodsDesc;

import java.util.List;

public interface GoodsService {
    void add(Goods goods);
    void update(Goods goods);
    Goods findById(Long id);
    void putAway(Long id, Boolean isMarketable);
    Page<Goods> search(Goods goods, int page, int size);
    List<GoodsDesc> findAll();
    GoodsDesc findDesc(Long id);
}
