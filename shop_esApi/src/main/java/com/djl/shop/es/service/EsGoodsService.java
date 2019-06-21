package com.djl.shop.es.service;

import com.djl.common.vo.R;
import com.djl.shop.es.model.EsGoods;

/**
 * @author DJL
 * @create 2019-06-21 19:35
 * @desc 搜索服务接口层
 **/
public interface EsGoodsService {
    // 商品信息的保存至ES服务器
    R save(EsGoods goods);

    // 查询ES服务器中所有信息
    R findAll();

    // 更具id删除某条记录
    void del(Integer id);

    // 更具条件查询数据
    R findByCondition(String name);


}
