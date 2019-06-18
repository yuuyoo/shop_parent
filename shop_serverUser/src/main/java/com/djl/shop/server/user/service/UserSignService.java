package com.djl.shop.server.user.service;

import com.djl.common.vo.R;

/**
 * @author DJL
 * @create 2019-06-16 16:19
 * @desc 用户签到服务接口
 **/
public interface UserSignService {

    /**
     * 新增签到记录
     * @param token
     * @return
     */
    R save(String token);

    /**
     * 根据用户相关的所有签到记录
     * @param token
     * @return
     */
    R findByUid(String token);

    /**
     * 查询用户最近一次的签到记录
     * @param token
     * @return
     */
    R findByUidLast(String token);

    /**
     * 查询用户最近几天内的签到记录
     * @param token
     * @param days 查询几天内的数据
     * @return
     */
    R findByUidDays(String token, Integer days);
}
