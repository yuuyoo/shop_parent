package com.djl.shop.resource.config;

import com.djl.shop.resource.util.UploadFileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DJL
 * @create 2019-06-22 21:09
 * @desc 创建七牛云上传文件对象
 **/
@Configuration
public class UploadFileUtilConfig {
    @Bean
    public UploadFileUtil createUpToken() {
        return new UploadFileUtil();
    }
}
