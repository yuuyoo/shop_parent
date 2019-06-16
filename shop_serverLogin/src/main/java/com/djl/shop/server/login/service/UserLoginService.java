package com.djl.shop.server.login.service;

import com.djl.common.vo.R;

/**
 * @author DJL
 * @create 2019-06-16 11:24
 * @desc 用户登录服务层接口
 **/
public interface UserLoginService {

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    R login(String phone, String password);

    /**
     * 验证登录有效性
     * @param token
     * @return
     */
    R checkLogin(String token);

    /**
     * 用户注销
     * @param token
     * @return
     */
    R logout(String token);
}
