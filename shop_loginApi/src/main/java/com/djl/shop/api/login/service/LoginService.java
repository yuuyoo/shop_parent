package com.djl.shop.api.login.service;

import com.djl.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DJL
 * @create 2019-06-16 14:24
 * @desc 用户登录服务接口
 **/
@FeignClient(name = "LoginProvider")
public interface LoginService {

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @PostMapping("/login/login.do")
    R login(@RequestParam("phone") String phone, @RequestParam("password") String password);

    /**
     * 验证登录有效性
     * @param token
     * @return
     */
    @GetMapping("/login/checklogin.do")
    R checkLogin(@RequestParam("token") String token);

    /**
     * 用户注销
     * @param token
     * @return
     */
    @GetMapping("/login/logout.do")
    R logout(@RequestParam("token") String token);
}
