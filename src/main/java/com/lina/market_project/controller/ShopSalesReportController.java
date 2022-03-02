package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.ShopSalesReport;
import com.lina.market_project.service.impl.ShopSalesReportServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/shop-sales-report")
public class ShopSalesReportController {
    @Resource
    ShopSalesReportServiceImpl shopSalesReportService;


    @GetMapping("/searchShopSalesReport")
    public Result<?> searchShopSalesReport(@RequestParam Integer selectItem,@RequestParam Integer shopId){
        List<ShopSalesReport> list=new ArrayList<>();
        if(selectItem==0){//七天内
            LocalDate localDate=LocalDate.now();

            for(int i=-7;i<=-1;i++){
                LocalDate fromDate=localDate.plusDays(i);
                System.out.println("一周内："+fromDate);
                QueryWrapper<ShopSalesReport> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("time",fromDate).eq("shop_id",shopId);

                ShopSalesReport shopSalesReport=shopSalesReportService.getOne(queryWrapper);

                if(shopSalesReport!=null){
                    list.add(shopSalesReport);
                }
            }
        }else {
            LocalDate localDate=LocalDate.now();

            for(int i=-30;i<=-1;i++){
                LocalDate fromDate=localDate.plusDays(i);

                System.out.println("一个月内："+fromDate);
                QueryWrapper<ShopSalesReport> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("time",fromDate).eq("shop_id",shopId);

                ShopSalesReport shopSalesReport=shopSalesReportService.getOne(queryWrapper);

                if(shopSalesReport!=null){
                    list.add(shopSalesReport);
                }
            }
        }
        return Result.success(list);
    }
}

