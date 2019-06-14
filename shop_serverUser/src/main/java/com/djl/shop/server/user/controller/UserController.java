package com.djl.shop.server.user.controller;

import com.djl.common.exception.UserException;
import com.djl.common.vo.R;
import com.djl.shop.entity.User;
import com.djl.shop.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DJL
 * @create 2019-06-13 22:58
 * @desc 用户相关控制器
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 新增用户注册
    @PostMapping("/user/add.do")
    public R save(@RequestBody User user) {

        try {
            // 注册成功
            userService.save(user);
            return R.setOK("注册成功");
        } catch (UserException e) {
            e.printStackTrace();
            return R.setERROR("注册失败");
        }
    }

    // 根据手机号查询指定用户
    @GetMapping("/user/find.do")
    public R findByPhone(@RequestParam("phone") String phone, @RequestParam("flag") Integer flag) {
        return userService.findByPhone(phone, flag);
    }

    // 检查手机号是否已被注册
    @GetMapping("/user/check.do")
    public R checkByPhone(@RequestParam("phone") String phone) {
        return userService.checkPhone(phone);
    }
}
