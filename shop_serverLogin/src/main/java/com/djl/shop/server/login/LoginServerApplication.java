package com.djl.shop.server.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author DJL
 * @create 2019-06-16 11:00
 * @desc 开关
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.djl.shop.server.login.dao")
public class LoginServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LoginServerApplication.class);
    }
}
