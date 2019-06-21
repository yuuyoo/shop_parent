package com.djl.shop.server.goods.dao;

import com.djl.shop.entity.GoodsRepertory;

public interface GoodsRepertoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsRepertory record);

    int insertSelective(GoodsRepertory record);

    GoodsRepertory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsRepertory record);

    int updateByPrimaryKey(GoodsRepertory record);
}