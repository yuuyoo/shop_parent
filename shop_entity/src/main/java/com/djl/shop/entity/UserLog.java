package com.djl.shop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserLog {
    private Long id;

    private Integer uid;

    private Integer flag;

    private Date createtime;

    private String content;

}