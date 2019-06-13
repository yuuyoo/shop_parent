package com.djl.shop.server.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author DJL
 * @create 2019-06-13 17:24
 * @desc 服务提供者服务开关
 **/
@SpringBootApplication
@EnableTransactionManagement    // 开启事务管理
@EnableEurekaClient // 服务器提供者
public class UserServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

    /**
     * 重写configure方法实现项目war运行
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserServerApplication.class);
    }
}
