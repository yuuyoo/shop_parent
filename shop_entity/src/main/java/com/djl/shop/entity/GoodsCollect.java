package com.djl.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsCollect {
    private Integer id;

    private Integer gid;

    private Integer uid;

    private Date createtime;

}