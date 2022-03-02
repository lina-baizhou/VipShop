package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.pojo.HarvestAddress;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.service.IHarvestAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-08-03
 */
@RestController
@RequestMapping("/harvest-address")
public class HarvestAddressController {
    @Resource
    IHarvestAddressService harvestAddressService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody HarvestAddress harvestAddress){
        harvestAddressService.save(harvestAddress);

        QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",harvestAddress.getUserId());

        return Result.success(harvestAddressService.list(wrapper));
    }

    @GetMapping("getHarvestAddressList")
    public Result<?> getHarvestAddressList(@RequestParam Integer userId){
        QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);

        return Result.success(harvestAddressService.list(wrapper));
    }

    @GetMapping("/getHarvestAddressById")
    public Result<?> getHarvestAddressById(@RequestParam Integer harvestNumber){
        return Result.success(harvestAddressService.getById(harvestNumber));
    }

    /**
     * 重新设置默认地址
     */
    @GetMapping("setDefaultAddress")
    public Result<?> setDefaultAddress(@RequestParam Integer oldAddressId, @RequestParam Integer newAddressId,@RequestParam Integer userId){
        UpdateWrapper<HarvestAddress> wrapper1=new UpdateWrapper<>();
        //设置旧的默认地址不为默认
        System.out.println(oldAddressId);
        System.out.println(newAddressId);
        System.out.println(userId);

        wrapper1.eq("harvest_id",oldAddressId).set("is_default",0);
        harvestAddressService.update(null,wrapper1);

        UpdateWrapper<HarvestAddress> wrapper2=new UpdateWrapper<>();
        //设置新的默认地址位默认
        wrapper2.eq("harvest_id",newAddressId).set("is_default",1);
        harvestAddressService.update(null,wrapper2);


        //返回新的序列
        QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        System.out.println(harvestAddressService.list(wrapper));


        return Result.success(harvestAddressService.list(wrapper));
    }

    @PostMapping("deleteAddress")
    public Result<?> deleteAddress(@RequestBody HarvestAddress harvestAddress){
        harvestAddressService.removeById(harvestAddress.getHarvestId());

        QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",harvestAddress.getUserId());

        return Result.success(harvestAddressService.list(wrapper));
    }

    @PostMapping("updateAddress")
    public Result<?> updateAddress(@RequestBody HarvestAddress harvestAddress){
        System.out.println(harvestAddress);
        if(harvestAddress.getIsDefault()==1){
            QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
            wrapper.eq("user_id",harvestAddress.getUserId());
            List<HarvestAddress> list= harvestAddressService.list(wrapper);

            for(HarvestAddress harvestAddress1:list){
                harvestAddress1.setIsDefault(0);
            }

            harvestAddressService.updateBatchById(list);
        }

        harvestAddressService.updateById(harvestAddress);

        QueryWrapper<HarvestAddress> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",harvestAddress.getUserId());
        List<HarvestAddress> list= harvestAddressService.list(wrapper);

        return Result.success(list);
    }
}

