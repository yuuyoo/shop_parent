package com.djl.shop.es.controller;

import com.djl.common.vo.R;
import com.djl.shop.es.model.EsGoods;
import com.djl.shop.es.service.EsGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-21 14:18
 * @desc ${DESCRIPTION}
 **/
@RestController
@Api(value = "统一ES服务API接口", tags = "提供ES服务的数据接口")
public class EsGoodsController {

    @Autowired
    private EsGoodsService esGoodsService;

    // 商品信息的保存至ES服务器
    @ApiOperation("保存商品数据至ES服务器")
    @PostMapping("/es/addgoods.do")
    R save(EsGoods goods){
        return esGoodsService.save(goods);
    }

    // 查询ES服务器中所有信息
    @ApiOperation("查询ES服务器中所有的商品数据")
    @PostMapping("/es/listgoods.do")
    R findAll(){
        return esGoodsService.findAll();
    }

    // 根据id删除某条记录
    @ApiOperation("删除ES服务器中指定的数据")
    @PostMapping("/es/del.do")
    void del(Integer id){
        esGoodsService.del(id);
    }

    // 更具条件查询数据
    @ApiOperation("条件查询ES服务器中所有的商品数据")
    @PostMapping("/es/findcondition.do")
    R findByCondition(String name){
        return esGoodsService.findByCondition(name);
    }
}
