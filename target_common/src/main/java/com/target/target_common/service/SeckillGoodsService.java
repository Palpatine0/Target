package com.target.target_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.SeckillGoods;


public interface SeckillGoodsService {
    void add(SeckillGoods seckillGoods);
    void update(SeckillGoods seckillGoods);
    Page<SeckillGoods> findPage(int page, int size);
}
