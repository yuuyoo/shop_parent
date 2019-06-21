package com.djl.shop.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DJL
 * @create 2019-06-21 20:24
 * @desc ${DESCRIPTION}
 **/
@SpringBootApplication
@EnableSwagger2
@EnableScheduling // 启动任务调度器
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }
}
