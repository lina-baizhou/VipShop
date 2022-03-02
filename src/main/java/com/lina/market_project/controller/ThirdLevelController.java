package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.ThirdLevel;
import com.lina.market_project.service.ISecondLevelService;
import com.lina.market_project.service.IThirdLevelService;
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
@RequestMapping("/third-level")
public class ThirdLevelController {
    @Resource
    IThirdLevelService thirdLevelService;

    @GetMapping("/getThirdLevelList")
    public Result<?> getThirdLevelList(@RequestParam Integer fatherId){
        QueryWrapper<ThirdLevel> wrapper=new QueryWrapper();
        wrapper.eq("father_id",fatherId);

        return Result.success(thirdLevelService.list(wrapper));
    }

    @GetMapping("/getThirdType")
    public Result<?> getThirdType(@RequestParam Integer id){
        return Result.success(thirdLevelService.getById(id));
    }
}

