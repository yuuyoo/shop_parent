package com.djl.shop.server.user.service;

import com.djl.common.exception.UserException;
import com.djl.common.vo.R;
import com.djl.shop.entity.User;

/**
 * @author DJL
 * @create 2019-06-13 21:53
 * @desc 用户相关服务接口
 **/
public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    R save(User user) throws UserException;

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @param flag 用户用户状态
     * @return
     */
    R findByPhone(String phone, Integer flag);

    /**
     * 检查手机号是否存在
     * @param phone 手机号
     * @return
     */
    R checkPhone(String phone);
}
