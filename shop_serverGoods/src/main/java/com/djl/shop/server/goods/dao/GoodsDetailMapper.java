package com.djl.shop.server.goods.dao;

import com.djl.shop.entity.GoodsDetail;

public interface GoodsDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDetail record);

    int insertSelective(GoodsDetail record);

    GoodsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsDetail record);

    int updateByPrimaryKeyWithBLOBs(GoodsDetail record);

    int updateByPrimaryKey(GoodsDetail record);
}