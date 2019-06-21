package com.djl.shop.server.goods.service.impl;

import com.djl.common.vo.R;
import com.djl.shop.entity.Goods;
import com.djl.shop.server.goods.dao.GoodsMapper;
import com.djl.shop.server.goods.dto.GoodsListDto;
import com.djl.shop.server.goods.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DJL
 * @create 2019-06-21 21:17
 * @desc ${DESCRIPTION}
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Override
    public R insert(Goods record) {

        return R.setOK("OK",goodsMapper.insert(record));
    }

    @Override
    @Transactional
    public R selectAll(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        PageInfo<GoodsListDto> pageInfo = new PageInfo<>(goodsMapper.selectAll());

        return R.setOK("OK", pageInfo);
    }

    @Override
    public R selectById(Integer id) {
        return R.setOK("OK", goodsMapper.selectById(id));
    }

    @Override
    public R selectDetail(Integer id) {

        return R.setOK("OK", goodsMapper.selectDetail(id));
    }
}
