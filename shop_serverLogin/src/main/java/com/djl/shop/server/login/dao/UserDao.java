package com.djl.shop.server.login.dao;

import com.djl.shop.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author DJL
 * @create 2019-06-16 11:10
 * @desc ${DESCRIPTION}
 **/
public interface UserDao {

    /**
     * 根据手机号查询指定用户
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    User selectByPhone(String phone);
}
