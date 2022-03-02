package com.lina.market_project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.pojo.Result;
import com.lina.market_project.pojo.User;
import com.lina.market_project.mapper.UserMapper;
import com.lina.market_project.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 白粥
 * @since 2021-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    public Result<?> modifyPassword(String password,Integer id){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",id).set("password", password);

        Integer rows = userMapper.update(null, updateWrapper);

        return Result.success(rows);
    }

    public Result<?> modifyImage(String image,Integer id){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",id).set("image", image);

        Integer rows = userMapper.update(null, updateWrapper);

        return Result.success(image);
    }
}
