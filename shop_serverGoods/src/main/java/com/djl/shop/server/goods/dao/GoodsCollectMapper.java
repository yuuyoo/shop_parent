package com.djl.shop.server.goods.dao;

import com.djl.shop.entity.GoodsCollect;

public interface GoodsCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCollect record);

    int insertSelective(GoodsCollect record);

    GoodsCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCollect record);

    int updateByPrimaryKey(GoodsCollect record);
}