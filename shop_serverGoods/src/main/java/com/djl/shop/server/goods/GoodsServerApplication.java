package com.djl.shop.server.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author DJL
 * @create 2019-06-19 9:37
 * @desc 商品服务开关
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class GoodsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServerApplication.class, args);
    }
}
