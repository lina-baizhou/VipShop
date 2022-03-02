package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.ReturnOrder;
import com.lina.market_project.service.impl.ReturnOrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/return-order")
public class ReturnOrderController {
    @Resource
    ReturnOrderServiceImpl returnOrderService;

    @PostMapping("/saveReturnOrder")
    public Result<?> saveReturnOrder(@RequestBody ReturnOrder returnOrder){
        return Result.success(returnOrderService.save(returnOrder));
    }

    @PostMapping("/removeReturnOrder")
    public Result<?> removeReturnOrder(@RequestBody ReturnOrder returnOrder){
        QueryWrapper<ReturnOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_number",returnOrder.getOrderNumber()).eq("goods_index",returnOrder.getGoodsIndex());
        return Result.success(returnOrderService.remove(queryWrapper));
    }
}

