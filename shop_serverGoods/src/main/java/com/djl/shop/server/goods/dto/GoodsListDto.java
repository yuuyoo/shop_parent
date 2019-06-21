package com.djl.shop.server.goods.dto;

import lombok.Data;


@Data
public class GoodsListDto {
    private Integer id;

    private String name;

    private Integer gtid;

    private String brand;

    private String discount;

    private Integer flag;

    private String picurl;
    private int minprice;
    private int paycount;
}
