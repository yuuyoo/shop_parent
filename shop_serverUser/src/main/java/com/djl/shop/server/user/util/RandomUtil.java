package com.djl.shop.server.user.util;

import java.util.Random;

/**
 * 随机数生成工具类
 */
public class RandomUtil {

    /**
     * 生成指定范围内的随机数
     * @param start 最小数
     * @param end 最大数
     * @return
     */
    public static int createNum(int start,int end){
        Random random=new Random();
        return random.nextInt(end-start)+start;
    }
}
