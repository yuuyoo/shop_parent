package com.djl.shop.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;

    private String name;

    private Integer gtid;

    private String brand;

    private String discount;

    private Integer flag;

    private String piccurl;

}