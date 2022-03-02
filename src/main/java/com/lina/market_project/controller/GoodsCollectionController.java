package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lina.market_project.pojo.Goods;
import com.lina.market_project.pojo.GoodsCollection;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.Shop;
import com.lina.market_project.service.IGoodsCollectionService;
import com.lina.market_project.service.IGoodsService;
import com.lina.market_project.service.impl.ShopServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/goods-collection")
public class GoodsCollectionController {
    @Resource
    IGoodsCollectionService goodsCollectionService;

    @Resource
    IGoodsService goodsService;

    @Resource
    ShopServiceImpl shopService;

    @GetMapping("/getGoodsCollectionFlag")
    public Result<?> getCollectionFlag(@RequestParam Integer userId,@RequestParam Integer goodsId){
        QueryWrapper<GoodsCollection> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("goods_id",goodsId);

        GoodsCollection goodsCollection=goodsCollectionService.getOne(queryWrapper);

        Boolean flag=false;
        if(goodsCollection!=null){
            flag=true;
        }
        return Result.success(flag);
    }

    @PostMapping("/saveGoodsCollection")
    public Result<?> saveGoodsCollection(@RequestBody GoodsCollection goodsCollection){
        Goods goods=goodsService.getById(goodsCollection.getGoodsId());
        Shop shop=shopService.getById(goods.getShopId());
        shop.setGoodsFansCount(shop.getGoodsFansCount()+1);
        shopService.updateById(shop);

        return Result.success(goodsCollectionService.save(goodsCollection));
    }

    @PostMapping("/deleteGoodsCollection")
    public Result<?> deleteGoodsCollection(@RequestBody GoodsCollection goodsCollection){
        Goods goods=goodsService.getById(goodsCollection.getGoodsId());
        Shop shop=shopService.getById(goods.getShopId());
        shop.setGoodsFansCount(shop.getGoodsFansCount()-1);
        shopService.updateById(shop);

        QueryWrapper<GoodsCollection> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",goodsCollection.getUserId()).eq("goods_id",goodsCollection.getGoodsId());
        return Result.success(goodsCollectionService.remove(queryWrapper));
    }

    @GetMapping("getGoodsCollectionList")
    public Result<?> getGoodsCollectionList(@RequestParam Integer userId){
        QueryWrapper<GoodsCollection> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("user_id",userId);

        List<GoodsCollection> list=goodsCollectionService.list(queryWrapper);

        List<Goods> goodsList=new ArrayList<>();

        for(GoodsCollection goodsCollection:list){
            QueryWrapper<Goods> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("goods_id",goodsCollection.getGoodsId());

            goodsList.add(goodsService.getOne(queryWrapper1));
        }
        // 反转lists
        Collections.reverse(goodsList);

        return Result.success(goodsList);
    }
}

