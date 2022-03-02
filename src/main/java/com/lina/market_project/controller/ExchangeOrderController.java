package com.lina.market_project.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.pojo.ExchangeOrder;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.ShopOrder;
import com.lina.market_project.service.impl.ExchangeOrderServiceImpl;
import com.lina.market_project.service.impl.ShopOrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-09-21
 */
@RestController
@RequestMapping("/exchange-order")
public class ExchangeOrderController {
    @Resource
    ExchangeOrderServiceImpl exchangeOrderService;

    @Resource
    ShopOrderServiceImpl shopOrderService;

    @PostMapping("/saveExchangeOrder")
    public Result<?> saveExchangeOrder(@RequestBody ExchangeOrder order){
        //--------------------------减少真实商品数量，防止退货跟换货同时进行-----------------------
        ShopOrder shopOrder=shopOrderService.getById(order.getOrderNumber());
        List<Integer> list= JSONArray.parseArray(shopOrder.getGoodsRealCount(),Integer.class);
        list.set(order.getGoodsIndex(),list.get(order.getGoodsIndex())-order.getGoodsCount());

        shopOrder.setGoodsRealCount(JSONArray.toJSONString(list));
        shopOrder.setTime(LocalDateTime.now());

        shopOrderService.updateById(shopOrder);


        return Result.success(exchangeOrderService.save(order));
    }

    @PostMapping("/cancelExchangeOrder")
    public Result<?> cancelExchangeOrder(@RequestBody ExchangeOrder order){
        QueryWrapper<ExchangeOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_number",order.getOrderNumber()).eq("specifications_id",order.getSpecificationsId());
        order=exchangeOrderService.getOne(queryWrapper);


        //--------------------------取消换货时，恢复真实商品数量-----------------------
        ShopOrder shopOrder=shopOrderService.getById(order.getOrderNumber());
        List<Integer> list= JSONArray.parseArray(shopOrder.getGoodsRealCount(),Integer.class);
        list.set(order.getGoodsIndex(),list.get(order.getGoodsIndex())+order.getGoodsCount());

        List<Integer> list1=JSONArray.parseArray(shopOrder.getReturnFlag(),Integer.class);
        list1.set(order.getGoodsIndex(),-1);

        shopOrder.setGoodsRealCount(JSONArray.toJSONString(list));
        shopOrder.setTime(LocalDateTime.now());
        shopOrder.setChangeFlag(JSONArray.toJSONString(list1));

        shopOrderService.updateById(shopOrder);

        QueryWrapper<ExchangeOrder> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("order_number",order.getOrderNumber());
        queryWrapper1.eq("specifications_id",order.getSpecificationsId());

        return Result.success(exchangeOrderService.remove(queryWrapper1));
    }

    @PostMapping("/removeExchangeOrder")
    public Result<?> removeExchangeOrder(@RequestBody ExchangeOrder order){
        QueryWrapper<ExchangeOrder> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("order_number",order.getOrderNumber());
        queryWrapper1.eq("specifications_id",order.getSpecificationsId());

        return Result.success(exchangeOrderService.remove(queryWrapper1));
    }

    @PostMapping("/modifyExchangeOrderForTakeDelivery")
    public Result<?> modifyExchangeOrderForTakeDelivery(@RequestBody ExchangeOrder order){
        UpdateWrapper<ExchangeOrder> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("order_number",order.getOrderNumber());
        updateWrapper.eq("specifications_id",order.getSpecificationsId());
        updateWrapper.set("exchange_order_number",order.getExchangeOrderNumber());

        return Result.success(exchangeOrderService.update(updateWrapper));
    }
}

