package com.lina.market_project.controller;


import com.lina.market_project.pojo.FirstLevel;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.service.IFirstLevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/first-level")
public class FirstLevelController {
    @Resource
    IFirstLevelService firstLevelService;

    @GetMapping("/getFirstLevelList")
    public Result<?> getFirstTypeList(){
        ArrayList<FirstLevel> firstLevelArrayList=new ArrayList<>();
        firstLevelArrayList= (ArrayList<FirstLevel>) firstLevelService.list();
        return Result.success(firstLevelArrayList);
    }
}

