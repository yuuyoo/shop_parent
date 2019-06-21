package com.djl.shop.shop_api.controller;

import com.djl.common.vo.R;
import com.djl.shop.entity.Goods;
import com.djl.shop.shop_api.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DJL
 * @create 2019-06-21 22:28
 * @desc ${DESCRIPTION}
 **/
@RestController
@Api(value = "操作商品的API接口", tags = "操作商品信息的数据接口")
public class GoosController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("新增商品的数据接口")
    @PostMapping("/goods/add.do")
    public R insert(@RequestBody Goods record){
        return goodsService.insert(record);
    }

    @ApiOperation("分页查询商品信息的数据接口")
    @GetMapping("/goods/findBypage.do")
    public R findByPage(@RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.findByPage(page, count);
    }

    @ApiOperation("根据商品id查询商品信息")
    @GetMapping("/goods/findById.do")
    public R selectById(@RequestParam("id") Integer id){
        return  goodsService.selectById(id);
    }

    @ApiOperation("根据商品id查询商品详情")
    @GetMapping("/goods/findDetail.do")
    public R selectDetail(@RequestParam("id") Integer id){
        return goodsService.selectDetail(id);
    }


}
