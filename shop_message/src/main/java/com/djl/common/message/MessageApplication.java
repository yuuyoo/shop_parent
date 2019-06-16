package com.djl.common.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DJL
 * @create 2019-06-13 20:53
 * @desc 开关类
 **/
@SpringBootApplication
@MapperScan("com.djl.common.message.core.dao")
@EnableTransactionManagement
@EnableSwagger2
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
