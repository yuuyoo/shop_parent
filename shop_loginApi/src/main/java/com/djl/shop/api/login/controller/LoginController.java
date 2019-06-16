package com.djl.shop.api.login.controller;

import com.djl.common.config.ProjectConfig;
import com.djl.common.vo.R;
import com.djl.shop.api.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DJL
 * @create 2019-06-16 14:24
 * @desc 用户登录控制器
 **/
@RestController
@Api(value = "用户登录API接口", tags = "用户登录API数据接口")
public class LoginController {

    @Autowired(required = false)
    private LoginService loginService;

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @ApiOperation("用户登录数据接口")
    @PostMapping("/api/login/login.do")
    public R login(String phone, String password) {
        return loginService.login(phone, password);
    }

    /**
     * 验证登录有效性
     * @param request servlet请求对象 将token字符串放在此对象中
     * @return
     */
    @ApiOperation("校验用户登录状态的数据接口")
    @GetMapping("/api/login/checklogin.do")
    public R checkLogin(HttpServletRequest request) {
        System.out.println(request.getHeader(ProjectConfig.TOKENHEAD));
        return loginService.checkLogin(request.getHeader(ProjectConfig.TOKENHEAD));
    }

    /**
     * 用户注销
     * @param request servlet请求对象 将token字符串放在此对象中
     * @return
     */
    @ApiOperation("用户注销的数据接口")
    @GetMapping("/api/login/logout.do")
    public R logout(HttpServletRequest request) {
        return loginService.logout(request.getHeader(ProjectConfig.TOKENHEAD));
    }
}
