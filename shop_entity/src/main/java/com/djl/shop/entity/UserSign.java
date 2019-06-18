package com.djl.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserSign {
    private Long id;

    private Integer uid;

    private Integer score;

    private Integer extrascore;

    private Integer days;

    private Date createtime;

}