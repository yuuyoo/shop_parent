package com.djl.shop.server.user.dao;

import com.djl.shop.entity.UserPoints;
import org.apache.ibatis.annotations.Param;

public interface UserPointsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPoints record);

    int insertSelective(UserPoints record);

    UserPoints selectByPrimaryKey(Integer id);

    void update(@Param("uid") Integer uid, @Param("score") Integer score);

    int updateByPrimaryKeySelective(UserPoints record);

    int updateByPrimaryKey(UserPoints record);
}