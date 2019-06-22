package com.djl.shop.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DJL
 * @create 2019-06-22 20:11
 * @desc ${DESCRIPTION}
 **/
@SpringBootApplication
@EnableSwagger2
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
