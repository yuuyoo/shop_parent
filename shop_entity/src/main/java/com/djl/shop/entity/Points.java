package com.djl.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Points {
    private Long id;

    private Integer uid;

    private Integer score;

    private String info;

    private Date startdate;

    private Date enddate;

    private Short flag;

}