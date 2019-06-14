package com.djl.shop.server.user.dao;

import com.djl.shop.entity.Points;

public interface PointsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Points record);

    int insertSelective(Points record);

    Points selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Points record);

    int updateByPrimaryKey(Points record);
}