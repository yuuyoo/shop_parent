package com.djl.shop.server.login.controller;

import com.djl.common.vo.R;
import com.djl.shop.server.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-16 14:11
 * @desc 登录控制器
 **/
@RestController
public class LoginController {

    @Autowired(required = false)
    private UserLoginService userLoginService;

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @PostMapping("/login/login.do")
    public R login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        return userLoginService.login(phone, password);
    }

    /**
     * 验证登录有效性
     * @param token
     * @return
     */
    @GetMapping("/login/checklogin.do")
    public R checkLogin(@RequestParam("token") String token) {
        return userLoginService.checkLogin(token);
    }

    /**
     * 用户注销
     * @param token
     * @return
     */
    @GetMapping("/login/logout.do")
    public R logout(@RequestParam("token") String token) {
        return userLoginService.logout(token);
    }
}
