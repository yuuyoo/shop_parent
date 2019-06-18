package com.djl.shop.shop_api.service;

import com.djl.common.vo.R;
import com.djl.shop.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DJL
 * @create 2019-06-14 19:09
 * @desc 用户相关服务
 **/
@FeignClient(name = "UserProvider")
public interface UserService {

    @PostMapping("/user/add.do")
    R save(User user);

    @GetMapping("/user/find.do")
    R findByPhone(@RequestParam("phone") String phone, @RequestParam("flag") Integer flag);

    @GetMapping("/user/check.do")
    R checkByPhone(@RequestParam("phone") String phone);

    @PostMapping("/user/sign.do")
    R saveSign(@RequestParam("token") String token);

    @GetMapping("/user/signList.do")
    R findSignByUid(@RequestParam("token") String token);

    @GetMapping("/user/lastSign.do")
    R findSignByUidLast(@RequestParam("token") String token);

    @GetMapping("/user/daysSign.do")
    R findSignByUidDays(@RequestParam("token") String token, @RequestParam("days") Integer days);
}
