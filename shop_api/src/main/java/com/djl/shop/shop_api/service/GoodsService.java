package com.djl.shop.shop_api.service;

import com.djl.common.vo.R;
import com.djl.shop.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DJL
 * @create 2019-06-21 22:06
 * @desc ${DESCRIPTION}
 **/
@FeignClient(name = "GoodsProvider")
public interface GoodsService {

    @PostMapping("/goods/add.do")
    public R insert(@RequestBody Goods record);

    @GetMapping("/goods/findBypage.do")
    public R findByPage(@RequestParam("page") Integer page, @RequestParam("count") Integer count);

    @GetMapping("/goods/findById.do")
    public R selectById(@RequestParam("id") Integer id);

    @GetMapping("/goods/findDetail.do")
    public R selectDetail(@RequestParam("id") Integer id);
}
