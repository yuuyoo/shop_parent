package com.djl.shop.shop_api.controller;

import com.djl.common.vo.R;
import com.djl.shop.entity.User;
import com.djl.shop.shop_api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-14 19:16
 * @desc 用户相关控制器
 **/
@RestController
@Api(value = "用户中心API接口", tags = "用户中心数据接口")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    // 新增用户
    @ApiOperation(value = "新增用户接口")
    @PostMapping("/user/add.do")
    R save(User user){
        return userService.save(user);
    }

    // 根据phone查询用户
    @ApiOperation(value = "根据手机号查询有效的用户信息")
    @GetMapping("/user/find.do")
    R findByPhone(String phone){
        return userService.findByPhone(phone, 1);
    }

    // 查询phone是否已被注册
    @ApiOperation(value = "查询手机是否已被注册")
    @GetMapping("/user/check.do")
    R checkByPhone(String phone) {
        return userService.checkByPhone(phone);
    }
}
