package com.djl.shop.server.goods.dao;

import com.djl.shop.entity.Goods;
import com.djl.shop.server.goods.dto.GoodsDetailDto;
import com.djl.shop.server.goods.dto.GoodsListDto;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    // 新增商品信息
    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    // 商品列表
    List<GoodsListDto> selectAll();

    // 根据编号查询商品
    Goods selectById(Integer id);

    // 根据商品编号查询商品详情
    GoodsDetailDto selectDetail(Integer id);


}