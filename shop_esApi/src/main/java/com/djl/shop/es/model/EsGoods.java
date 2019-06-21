package com.djl.shop.es.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author DJL
 * @create 2019-06-21 10:15
 * @desc ${DESCRIPTION}
 **/
@Data
@Document(indexName = "ljb:allgoods", type="esgoods", shards = 5)
public class EsGoods {
    // @Id
    private Integer id;
    // @Field()
    private String goodsName;
    private String goodsType;
}
