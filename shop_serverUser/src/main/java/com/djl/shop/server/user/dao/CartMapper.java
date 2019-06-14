package com.djl.shop.server.user.dao;

import com.djl.shop.entity.Cart;

public interface CartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}