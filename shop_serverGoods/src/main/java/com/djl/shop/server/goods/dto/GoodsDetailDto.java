package com.djl.shop.server.goods.dto;

import lombok.Data;

import java.util.List;


@Data
public class GoodsDetailDto extends GoodsListDto {
    private List<GoodsSkuDto> skuDtoList;
}
