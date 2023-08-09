package com.target.target_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.target.target_common.pojo.SeckillGoods;
import com.target.target_common.result.BaseResult;
import com.target.target_common.service.SeckillGoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {
    @DubboReference
    private SeckillGoodsService seckillGoodsService;

    @PostMapping("/add")
    public BaseResult add(@RequestBody SeckillGoods seckillGoods){
        seckillGoodsService.add(seckillGoods);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody SeckillGoods seckillGoods){
        seckillGoodsService.update(seckillGoods);
        return BaseResult.ok();
    }

    @GetMapping("/findPage")
    public BaseResult<Page<SeckillGoods>> findPage(int page,int size){
        Page<SeckillGoods> seckillGoodsPage = seckillGoodsService.findPage(page, size);
        return BaseResult.ok(seckillGoodsPage);
    }
}