package com.djl.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsOrder {
    private Integer id;

    private Integer uid;

    private Integer payprice;

    private Integer discountprice;

    private Integer flag;

    private Date createtime;

    private Date modtime;


}