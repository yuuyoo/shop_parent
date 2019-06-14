package com.djl.common.jwt;


import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author DJL
 * @create 2019-06-14 10:10
 * @desc JWT Token 工具类实现
 **/
public class JwtUtil {

    // 生成token
    public static String createJWT() {

        // 获取指定签名加密算法的枚举对象 SHA256
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

        return null;
    }

    // 生成密码

    // 验证token
    public static boolean checkJWT(String token) {

        return true;
    }

    // 解析token
    public static String parseJWT(String token) {
        return null;
    }

    // 更新失效时间
    public static String updateJWT(String token) {
        return null;
    }

}
