package com.djl.shop.entity;

import lombok.Data;

@Data
public class Cart {
    private Long id;

    private Integer uid;

    private Integer maxcount;

    private Integer currcount;

}