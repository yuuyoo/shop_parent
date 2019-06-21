package com.djl.shop.es.dao;

import com.djl.shop.es.model.EsGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author DJL
 * @create 2019-06-21 10:15
 * @desc ${DESCRIPTION}
 **/
public interface EsGoosDao extends ElasticsearchRepository<EsGoods, Integer> {
}
