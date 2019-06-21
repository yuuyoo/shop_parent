package com.djl.shop.entity;

import lombok.Data;

@Data
public class GoodsType {
    private Integer id;

    private String name;

    private Integer parentid;

    private Integer level;

    private String info;

}