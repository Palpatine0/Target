package com.target.target_goods_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.target.target_common.pojo.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends BaseMapper<Goods> {
    void addGoodsSpecificationOption(@Param("gid") Long gid, @Param("optionId") Long optionId);
    void deleteGoodsSpecificationOption(Long gid);
    void putAway(@Param("id") Long id, @Param("isMarketable") Boolean isMarketable);
    Goods findById(long id);
}
