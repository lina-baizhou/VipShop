package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.lina.market_project.pojo.BrowsingHistory;
import com.lina.market_project.pojo.Goods;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.Shop;
import com.lina.market_project.service.impl.BrowsingHistoryServiceImpl;
import com.lina.market_project.service.impl.GoodsServiceImpl;
import com.lina.market_project.service.impl.ShopServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-09-09
 */
@RestController
@RequestMapping("/browsing-history")
public class BrowsingHistoryController {
    @Resource
    BrowsingHistoryServiceImpl browsingHistoryService;

    @Resource
    ShopServiceImpl shopService;

    @Resource
    GoodsServiceImpl goodsService;

    @PostMapping("/saveBrowsingHistory")
    public Result<?> saveBrowsingHistory(@RequestBody BrowsingHistory browsingHistory){
        browsingHistory.setTime(LocalDateTime.now());
        browsingHistory.setDeleted(0);

        Shop shop=shopService.getById(browsingHistory.getShopId());
        shop.setBrowseCount(shop.getBrowseCount()+1);
        shopService.updateById(shop);

        return Result.success(browsingHistoryService.saveOrUpdate(browsingHistory));
    }

    @GetMapping("/getBrowsingHistoryList")
    public Result<?> getBrowsingHistoryList(@RequestParam Integer userId){
        QueryWrapper<BrowsingHistory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByDesc("time").eq("deleted",0);

        List<BrowsingHistory> list=new ArrayList<>();
        list=browsingHistoryService.list(queryWrapper);

        List<Goods> goodsList=new ArrayList<>();
        for(BrowsingHistory browsingHistory:list){
            goodsList.add(goodsService.getById(browsingHistory.getGoodsId()));
        }

        return Result.success(goodsList);
    }


    @GetMapping("/deleteBrowsingHistory")
    public Result<?> deleteBrowsingHistory(@RequestParam Integer userId, @RequestParam Integer goodsId){
        UpdateWrapper<BrowsingHistory> queryWrapper=new UpdateWrapper<>();

        queryWrapper.eq("goods_id",goodsId).eq("user_id",userId).set("deleted",1);

        return Result.success(browsingHistoryService.update(queryWrapper));
    }

    @GetMapping("/deleteBrowsingHistoryList")
    public Result<?> deleteBrowsingHistoryList(@RequestParam Integer userId, @RequestParam("goodsIdList") Integer[] goodsIdList){
        for(Integer goodsId:goodsIdList){
            UpdateWrapper<BrowsingHistory> queryWrapper=new UpdateWrapper<>();

            queryWrapper.eq("goods_id",goodsId).eq("user_id",userId).set("deleted",1);

            browsingHistoryService.update(queryWrapper);
        }
        return Result.success();
    }
}

