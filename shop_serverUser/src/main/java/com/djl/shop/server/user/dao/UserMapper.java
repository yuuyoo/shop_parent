package com.djl.shop.server.user.dao;

import com.djl.shop.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 按手机号和账户状态查询用户
     * @param phone 用户手机号
     * @param flag 用户标记位 1：有效 2：失效
     * @return
     */
    User selectByPhone(@Param("phone") String phone, @Param("flag") Integer flag);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}