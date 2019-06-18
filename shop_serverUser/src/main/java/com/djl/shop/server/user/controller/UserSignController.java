package com.djl.shop.server.user.controller;

import com.djl.common.vo.R;
import com.djl.shop.server.user.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-18 11:20
 * @desc 用户签到控制器
 **/
@RestController
public class UserSignController {

    @Autowired(required = false)
    private UserSignService userSignService;

    /**
     * 新增签到记录
     * @param token
     * @return
     */
    @PostMapping("/user/sign.do")
    R save(@RequestParam("token") String token) {
        return userSignService.save(token);
    }

    /**
     * 根据用户相关的所有签到记录
     * @param token
     * @return
     */
    @GetMapping("/user/signList.do")
    R findByUid(@RequestParam("token") String token) {
        return userSignService.findByUid(token);
    }

    /**
     * 查询用户最近一次的签到记录
     * @param token
     * @return
     */
    @GetMapping("/user/lastSign.do")
    R findByUidLast(@RequestParam("token") String token) {
        return userSignService.findByUidLast(token);
    }

    /**
     * 查询用户最近几天内的签到记录
     * @param token
     * @param days 查询几天内的数据
     * @return
     */
    @GetMapping("/user/daysSign.do")
    R findByUidDays(@RequestParam("token") String token, @RequestParam("days") Integer days) {
        return userSignService.findByUidDays(token, days);
    }
}
