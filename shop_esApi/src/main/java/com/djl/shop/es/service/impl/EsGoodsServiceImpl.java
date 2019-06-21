package com.djl.shop.es.service.impl;

import com.djl.common.vo.R;
import com.djl.shop.es.dao.EsGoosDao;
import com.djl.shop.es.model.EsGoods;
import com.djl.shop.es.service.EsGoodsService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DJL
 * @create 2019-06-21 19:38
 * @desc ES商品服务接口实现类
 **/
@Service
public class EsGoodsServiceImpl implements EsGoodsService {

    @Autowired
    private EsGoosDao esGoosDao;

    @Override
    public R save(EsGoods goods) {
        if (esGoosDao.save(goods) != null) {
            return R.setOK();
        } else {
            return R.setERROR("网络错误");
        }

    }

    @Override
    public R findAll() {
        return R.setOK("OK", esGoosDao.findAll());
    }

    @Override
    public void del(Integer id) {
        esGoosDao.deleteById(id);
    }

    @Override
    public R findByCondition(String name) {
        // 参数合法性判断
        if (name != null && (name.length() > 0)){
            name = "*" + name + "*";
        } else {
            name = "*";
        }

        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name", name);

        return R.setOK("OK", esGoosDao.search(wildcardQueryBuilder));
    }
}
