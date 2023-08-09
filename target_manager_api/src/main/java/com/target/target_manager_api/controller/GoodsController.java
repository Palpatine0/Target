package com.target.target_manager_api.controller;

import com.target.target_common.pojo.Goods;
import com.target.target_common.result.BaseResult;
import com.target.target_common.service.GoodsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @DubboReference
    private GoodsService goodsService;
    @PostMapping("/add")
    public BaseResult add(@RequestBody Goods goods){
        goodsService.add(goods);
        return BaseResult.ok();
    }
    @PutMapping("/update")
    public BaseResult update(@RequestBody Goods goods){
        goodsService.update(goods);
        return BaseResult.ok();
    }
    @PutMapping("/putAway")
    public BaseResult putAway(Long id,boolean isMarketable){
        goodsService.putAway(id,isMarketable);
        return BaseResult.ok();
    }
    @GetMapping("/findById")
    public BaseResult<Goods> findById(Long id){
        Goods goods = goodsService.findById(id);
        return BaseResult.ok(goods);
    }
    @GetMapping("/search")
    public BaseResult<Page<Goods>> search(Goods goods, int page, int size){
        Page<Goods> search = goodsService.search(goods, page, size);
        return BaseResult.ok(search);
    }


}
