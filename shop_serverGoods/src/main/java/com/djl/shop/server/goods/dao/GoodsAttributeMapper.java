package com.djl.shop.server.goods.dao;

import com.djl.shop.entity.GoodsAttribute;

public interface GoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);
}