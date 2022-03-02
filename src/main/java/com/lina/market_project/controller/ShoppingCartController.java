package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.GoodsServiceImpl;
import com.lina.market_project.service.impl.GoodsSpecificationsServiceImpl;
import com.lina.market_project.service.impl.ShopServiceImpl;
import com.lina.market_project.service.impl.ShoppingCartServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Resource
    ShoppingCartServiceImpl shoppingCartService;

    @Resource
    GoodsServiceImpl goodsService;

    @Resource
    ShopServiceImpl shopService;

    @Resource
    GoodsSpecificationsServiceImpl goodsSpecificationsService;
    /**
     * 加入购物车
     * 加入时自动合并
     * @param shoppingCart
     * @return
     */
    @PostMapping("/saveShoppingCart")
    public Result<?> saveShoppingCart(@RequestBody ShoppingCart shoppingCart){
        System.out.println("shoppingCart"+shoppingCart);
        QueryWrapper<ShoppingCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",shoppingCart.getUserId()).eq("goods_id",shoppingCart.getGoodsId()).eq("specifications_id",shoppingCart.getSpecificationsId());

        ShoppingCart shoppingCart1=shoppingCartService.getOne(queryWrapper);

        System.out.println("shoppingCart:"+shoppingCart);
        if(shoppingCart1!=null){
            System.out.println("shoppingCart1:"+shoppingCart1);
            shoppingCart.setCount(shoppingCart.getCount()+shoppingCart1.getCount());
            shoppingCartService.removeById(shoppingCart1.getTime());
        }


        shoppingCart.setTime(LocalDateTime.now());

        return Result.success(shoppingCartService.save(shoppingCart));
    }

    @PostMapping("/updateShoppingCart")
    public Result<?> updateShoppingCart(@RequestBody ShoppingCart shoppingCart){
        UpdateWrapper<ShoppingCart> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("user_id",shoppingCart.getUserId()).eq("goods_id",shoppingCart.getGoodsId()).eq("specifications_id",shoppingCart.getSpecificationsId());
        shoppingCart.setTime(LocalDateTime.now());

        return Result.success(shoppingCartService.update(shoppingCart,updateWrapper));
    }

    @PostMapping("/removeShoppingCart")
    public Result<?> removeShoppingCart(@RequestBody ShoppingCardSearchItem shoppingCardSearchItem){
        QueryWrapper<ShoppingCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",shoppingCardSearchItem.getGoodsId()).eq("specifications_id",shoppingCardSearchItem.getSpecificationsId()).eq("user_id",shoppingCardSearchItem.getUserId());
        shoppingCartService.remove(queryWrapper);
        return Result.success();
    }


    /**
     * 批量移除购物车
     * @param shoppingCardSearchItemList
     * @return
     */
    @PostMapping("/removeShoppingCartList")
    public Result<?> removeShoppingCartList(@RequestBody ShoppingCardSearchItemList shoppingCardSearchItemList){
        for(ShoppingCardSearchItem shoppingCardSearchItem:shoppingCardSearchItemList.getList()){
            QueryWrapper<ShoppingCart> queryWrapper=new QueryWrapper<>();

            queryWrapper.eq("goods_id",shoppingCardSearchItem.getGoodsId()).eq("specifications_id",shoppingCardSearchItem.getSpecificationsId()).eq("user_id",shoppingCardSearchItem.getUserId());

            shoppingCartService.remove(queryWrapper);
        }
        return Result.success();
    }

    /**
     * 得到购物车里的商品
     * @param userId
     * @return
     */
    @GetMapping("/selectShoppingCartList")
    public Result<?> selectShoppingCartList(@RequestParam Integer userId){
        QueryWrapper<ShoppingCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).groupBy("shop_id").orderByDesc("time");

        List<ShoppingCart> list=shoppingCartService.list(queryWrapper);

        List<ShoppingCartGroupByShop> shoppingCartGroupByShopList=new ArrayList<>();
        for(ShoppingCart shoppingCart:list){
            ShoppingCartGroupByShop shoppingCartGroupByShop=new ShoppingCartGroupByShop();

            //获取shop信息
            QueryWrapper<Shop> shopQueryWrapper=new QueryWrapper<>();
            shopQueryWrapper.eq("shop_id",shoppingCart.getShopId());
            shoppingCartGroupByShop.setShop(shopService.getOne(shopQueryWrapper));


            //获取商品信息
            QueryWrapper<ShoppingCart> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("shop_id",shoppingCart.getShopId()).orderByDesc("time");
            List<ShoppingCart> shoppingCartList=shoppingCartService.list(queryWrapper1);


            List<GoodsSpecifications> goodsSpecificationsList=new ArrayList<>();
            List<Goods> goodsList=new ArrayList<>();
            List<Integer> countList=new ArrayList<>();
            for (ShoppingCart shoppingCart1:shoppingCartList){
                QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
                goodsQueryWrapper.eq("goods_id",shoppingCart1.getGoodsId());
                goodsList.add(goodsService.getOne(goodsQueryWrapper));

                //获取商品规格信息
                QueryWrapper<GoodsSpecifications> goodsSpecificationsQueryWrapper=new QueryWrapper<>();
                goodsSpecificationsQueryWrapper.eq("specifications_id",shoppingCart1.getSpecificationsId());
                goodsSpecificationsList.add(goodsSpecificationsService.getOne(goodsSpecificationsQueryWrapper));

                //获取数量信息
                countList.add(shoppingCart1.getCount());
            }
            shoppingCartGroupByShop.setGoodsList(goodsList);
            shoppingCartGroupByShop.setGoodsSpecificationsList(goodsSpecificationsList);
            shoppingCartGroupByShop.setGoodsCountList(countList);

            shoppingCartGroupByShopList.add(shoppingCartGroupByShop);
        }

        return Result.success(shoppingCartGroupByShopList);
    }
}

