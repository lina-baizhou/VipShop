package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.pojo.*;
import com.lina.market_project.service.impl.GoodsServiceImpl;
import com.lina.market_project.service.impl.GoodsSpecificationsServiceImpl;
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
 * @since 2021-08-18
 */
@RestController
@RequestMapping("/goods-specifications")
public class GoodsSpecificationsController {
    @Resource
    GoodsSpecificationsServiceImpl goodsSpecificationsService;

    @Resource
    GoodsServiceImpl goodsService;

    @PostMapping("/saveGoodsSpecifications")
    public Result<?> saveGoodsSpecifications(@RequestBody GoodsSpecificationsList goodsSpecificationsList){
        Float min=goodsSpecificationsList.getList().get(0).getGoodsPrice();
        for(GoodsSpecifications goodsSpecifications:goodsSpecificationsList.getList()){
            goodsSpecifications.setSpecificationsId(0);
            goodsSpecificationsService.save(goodsSpecifications);
            if(min>goodsSpecifications.getGoodsPrice()){
                min=goodsSpecifications.getGoodsPrice();
            }
        }
        UpdateWrapper<Goods> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("goods_price",min).eq("goods_id",goodsSpecificationsList.getList().get(0).getGoodsId());
        goodsService.update(updateWrapper);
        return Result.success();
    }

    @PostMapping("/modifyGoodsSpecifications")
    public Result<?> modifyGoodsSpecifications(@RequestBody GoodsSpecificationsList goodsSpecificationsList){
        Float min=goodsSpecificationsList.getList().get(0).getGoodsPrice();
        for(GoodsSpecifications goodsSpecifications:goodsSpecificationsList.getList()){
            if(goodsSpecifications.getSpecificationsId()==null){
                goodsSpecifications.setSpecificationsId(0);
                goodsSpecificationsService.save(goodsSpecifications);
            }else {
                goodsSpecificationsService.updateById(goodsSpecifications);
            }

            if(min>goodsSpecifications.getGoodsPrice()){
                min=goodsSpecifications.getGoodsPrice();
            }
        }
        UpdateWrapper<Goods> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("goods_price",min).eq("goods_id",goodsSpecificationsList.getList().get(0).getGoodsId());
        goodsService.update(updateWrapper);
        return Result.success();
    }


    @GetMapping("/selectGoodsSpecifications")
    public Result<?> selectGoodsSpecifications(@RequestParam Integer goodsId,@RequestParam String goodsType1,@RequestParam String goodsType2){
        QueryWrapper<GoodsSpecifications> queryWrapper=new QueryWrapper<>();
        if(goodsType2.equals("null")){
            queryWrapper.eq("goods_id",goodsId).like("goods_type1",goodsType1);
            return Result.success(goodsSpecificationsService.getOne(queryWrapper));
        }else {
            queryWrapper.eq("goods_id",goodsId).like("goods_type1",goodsType1).like("goods_type2",goodsType2);
            return Result.success(goodsSpecificationsService.getOne(queryWrapper));
        }
    }

    @GetMapping("/getGoodsSpecificationsByGoodsId")
    public Result<?> getGoodsSpecificationsByGoodsId(@RequestParam Integer goodsId){
        QueryWrapper<GoodsSpecifications> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId);

        return Result.success(goodsSpecificationsService.list(queryWrapper));
    }

    @GetMapping("/getTypeInf")
    public Result<?> getTypeInf(@RequestParam Integer goodsId){
        QueryWrapper<GoodsSpecifications> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId).groupBy("goods_type1");
        List<GoodsSpecifications> goodsSpecificationsList=goodsSpecificationsService.list(queryWrapper);

        String type1Item="";
        List<String> type1List=new ArrayList<>();

        Boolean flag=true;
        for(int i=0;i<goodsSpecificationsList.size();i++){
            String[] strings=goodsSpecificationsList.get(i).getGoodsType1().split(":");
            type1List.add(strings[1]);
            if(i==0){
                type1Item=strings[0];
                if(goodsSpecificationsList.get(i).getGoodsType2()==null){
                    flag=false;
                }
            }
        }

        String type2Item="";
        List<String> type2List=new ArrayList<>();

        if(flag==true){
            QueryWrapper<GoodsSpecifications> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("goods_id",goodsId).groupBy("goods_type2");
            List<GoodsSpecifications> goodsSpecificationsList1=goodsSpecificationsService.list(queryWrapper1);

            for(int i=0;i<goodsSpecificationsList1.size();i++){
                String[] strings=goodsSpecificationsList1.get(i).getGoodsType2().split(":");
                type2List.add(strings[1]);
                if(i==0){
                    type2Item=strings[0];
                }
            }
        }

        QueryWrapper<GoodsSpecifications> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("goods_id",goodsId);
        List<GoodsSpecifications> goodsSpecificationsList2=goodsSpecificationsService.list(queryWrapper2);

        Map<String,Object> map=new HashMap<>();
        map.put("type1Item",type1Item);
        map.put("type1List",type1List);
        map.put("type2Item",type2Item);
        map.put("type2List",type2List);
        map.put("goodsSpecificationsList",goodsSpecificationsList2);

       return Result.success(map);
    }
}



