package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.GoodsServiceImpl;
import com.lina.market_project.service.impl.ShopCollectionServiceImpl;
import com.lina.market_project.service.impl.ShopServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
 * @since 2021-09-10
 */
@RestController
@RequestMapping("/shop-collection")
public class ShopCollectionController {
    @Resource
    ShopCollectionServiceImpl shopCollectionService;

    @Resource
    ShopServiceImpl shopService;

    @Resource
    GoodsServiceImpl goodsService;

    @PostMapping("/saveShopCollection")
    public Result<?> saveShopCollection(@RequestBody ShopCollection shopCollection){
        Shop shop=shopService.getById(shopCollection.getShopId());
        shop.setFansNumber(shop.getFansNumber()+1);
        shopService.updateById(shop);
        return Result.success(shopCollectionService.save(shopCollection));
    }

    @PostMapping("/selectShopCollection")
    public Result<?> selectShopCollection(@RequestBody ShopCollection shopCollection){
        QueryWrapper<ShopCollection> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopCollection.getShopId()).eq("user_id",shopCollection.getUserId());
        return Result.success(shopCollectionService.getOne(queryWrapper));
    }

    @PostMapping("/removeShopCollection")
    public Result<?> removeShopCollection(@RequestBody ShopCollection shopCollection){
        Shop shop=shopService.getById(shopCollection.getShopId());
        shop.setFansNumber(shop.getFansNumber()-1);
        shopService.updateById(shop);
        
        QueryWrapper<ShopCollection> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",shopCollection.getUserId()).eq("shop_id",shopCollection.getShopId());

        return Result.success(shopCollectionService.remove(queryWrapper));
    }

    @GetMapping("/getShopCollectionList")
    public Result<?> getShopCollectionList(@RequestParam Integer userId){
        QueryWrapper<ShopCollection> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        List<ShopCollection> list=shopCollectionService.list(queryWrapper);

        List<Map<String,Object>> list1=new ArrayList<>();
        for(ShopCollection shopCollection:list){
            Map<String,Object> map=new HashMap<>();

            map.put("shop",shopService.getById(shopCollection.getShopId()));

            List<Goods> goodsList=new ArrayList<>();
            QueryWrapper<Goods> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("shop_id",shopCollection.getShopId());

            map.put("goodsList",goodsService.list(queryWrapper1));

            list1.add(map);
        }

        return Result.success(list1);
    }
}

