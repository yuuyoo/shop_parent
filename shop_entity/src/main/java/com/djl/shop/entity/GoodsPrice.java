package com.djl.shop.entity;

import lombok.Data;

@Data
public class GoodsPrice {
    private Integer id;

    private Integer gid;

    private String skuids;

    private Integer currprice;

    private Integer oldprice;

}