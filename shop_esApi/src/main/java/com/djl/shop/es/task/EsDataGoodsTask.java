package com.djl.shop.es.task;

import com.alibaba.fastjson.JSON;
import com.djl.common.config.ProjectConfig;
import com.djl.common.util.JedisUtil;
import com.djl.shop.es.model.EsGoods;
import com.djl.shop.es.service.EsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author DJL
 * @create 2019-06-21 15:36
 * @desc 基于Spring Task 实现ES服务数据同步 从Redis更新到ES服务器
 **/
@Component
public class EsDataGoodsTask {

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private EsGoodsService esGoodsService;

    // 每天间隔4个小时执行一次任务
    @Scheduled(cron = "0 0 0/4 * * ?")
    public void dataSync() {
        /*
         *   实现数据同步,从redis更新到es服务器
         */
        // 验证redis记录，新增商品的Key是否存在
        if (jedisUtil.exists(ProjectConfig.ESGOODSADD)) {
            // key存在，将数据同步到ES服务器中
            readToSave(jedisUtil.hgetall(ProjectConfig.ESGOODSADD), 1);
        } else {
            // 主key不存在，验证备份key
            if (jedisUtil.exists(ProjectConfig.ESGOODSADDBU)) {
                readToSave(jedisUtil.hgetall(ProjectConfig.ESGOODSADDBU), 1);
            }
        }

        // 修改逻辑处理
        // 验证redis记录，新增商品的Key是否存在
        if (jedisUtil.exists(ProjectConfig.ESGOODSUPDATE)) {
            // key存在，将数据同步到ES服务器中
            readToSave(jedisUtil.hgetall(ProjectConfig.ESGOODSUPDATE), 2);
        } else {
            // 主key不存在，验证备份key
            if (jedisUtil.exists(ProjectConfig.ESGOODSUPDATEBU)) {
                readToSave(jedisUtil.hgetall(ProjectConfig.ESGOODSUPDATEBU), 2);
            }
        }

        // 删除逻辑处理
        if (jedisUtil.exists(ProjectConfig.ESGOODSDELETE)) {
            del(jedisUtil.hgetall(ProjectConfig.ESGOODSDELETE));
        } else {
            // 验证备份key
            if (jedisUtil.exists(ProjectConfig.ESGOODSADDBU)) {
                del(jedisUtil.hgetall(ProjectConfig.ESGOODSADDBU));
            }
        }

    }

    // 新增数据时调用的方法
    private void readToSave(Map<String, String> map, Integer type) {
        if (map != null) {
            Collection<String> list = map.values();
            for (String j:list) {
                esGoodsService.save(JSON.parseObject(j, EsGoods.class));
            }

            // 清空key
            if (type == 1) {
                // 清理添加key
                jedisUtil.del(ProjectConfig.ESGOODSADD, ProjectConfig.ESGOODSADDBU);
            } else if (type == 2) {
                // 清理添加和更新key
                jedisUtil.del(ProjectConfig.ESGOODSUPDATE, ProjectConfig.ESGOODSUPDATEBU);
            }
        }
    }

    // 删除数据是调用的方法
    private void del(Map<String, String> map) {
        if (map != null) {
            Set<String> set = map.keySet();
            for (String id : set) {
                esGoodsService.del(Integer.parseInt(id));
            }

            // 存储之后，清除key
            jedisUtil.del(ProjectConfig.ESGOODSDELETE, ProjectConfig.ESGOODSDETETEBU);
        }
    }
}
