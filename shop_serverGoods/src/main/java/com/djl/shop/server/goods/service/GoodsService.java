package com.djl.shop.server.goods.service;

import com.djl.common.vo.R;
import com.djl.shop.entity.Goods;
import org.springframework.stereotype.Service;

/**
 * @author DJL
 * @create 2019-06-21 11:52
 * @desc ${DESCRIPTION}
 **/
@Service
public interface GoodsService {

    // 新增商品信息
    R insert(Goods record);

    // 商品列表
    R selectAll(Integer page, Integer count);

    // 根据编号查询商品
    R selectById(Integer id);

    // 根据商品编号查询商品详情
    R selectDetail(Integer id);
}
