package com.djl.shop.server.user.dao;

import com.djl.shop.entity.UserPoints;

public interface UserPointsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPoints record);

    int insertSelective(UserPoints record);

    UserPoints selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPoints record);

    int updateByPrimaryKey(UserPoints record);
}