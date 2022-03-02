package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.SecondLevel;
import com.lina.market_project.service.ISecondLevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/second-level")
public class SecondLevelController {
    @Resource
    ISecondLevelService secondLevelService;

    @GetMapping("/getSecondLevelList")
    public Result<?> getSecondLevelList(@RequestParam Integer fatherId){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("father_id",fatherId);

        return Result.success(secondLevelService.list(wrapper));
    }

    @GetMapping("/getSecondLevel")
    public Result<?> getSecondLevel(@RequestParam Integer id){
        return Result.success(secondLevelService.getById(id));
    }
}

