package com.djl.shop.server.user.dao;

import com.djl.shop.entity.UserDetail;

public interface UserDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
}