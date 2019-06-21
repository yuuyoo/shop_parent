package com.djl.shop.server.goods.controller;

import com.djl.common.vo.R;
import com.djl.shop.entity.Goods;
import com.djl.shop.server.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DJL
 * @create 2019-06-21 21:58
 * @desc ${DESCRIPTION}
 **/
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods/add.do")
    public R insert(@RequestBody Goods record) {

        return goodsService.insert(record);
    }

    @GetMapping("/goods/findBypage.do")
    public R findByPage(@RequestParam("page") Integer page, @RequestParam("count") Integer count) {

        return goodsService.selectAll(page, count);
    }

    @GetMapping("/goods/findById.do")
    public R selectById(@RequestParam("id") Integer id) {

        return goodsService.selectById(id);
    }

    @GetMapping("/goods/findDetail.do")
    public R selectDetail(@RequestParam("id") Integer id) {

        return goodsService.selectDetail(id);
    }
}
