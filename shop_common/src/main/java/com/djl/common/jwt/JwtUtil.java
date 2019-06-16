package com.djl.common.jwt;


import com.djl.common.config.ProjectConfig;
import com.djl.common.util.TimeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author DJL
 * @create 2019-06-14 10:10
 * @desc JWT Token 工具类实现
 **/
public class JwtUtil {

    /**
     * 基于JWT,生成令牌
     * @param id 令牌序号
     * @param content 存储的内容
     * @return
     */
    public static String createJWT(String id, String content) {
        // 获取指定签名加密算法的枚举对象 SHA256
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder();
        builder.setId(id);
        // 主题
        builder.setSubject(content);
        // 开始时间
        builder.setIssuedAt(new Date());
        // 过期时间
        builder.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIME));
        builder.signWith(algorithm, createKey());

        return builder.compact();
    }

    // 生成密码
    private static SecretKey createKey() {
        byte[] dataKey = ProjectConfig.JWTKEY.getBytes();
        return new SecretKeySpec(dataKey, 0, dataKey.length, "AES");
    }

    // 验证token
    public static boolean checkJWT(String token) {
        SecretKey key = createKey();
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 解析token
    public static String parseJWT(String token) {
        SecretKey key = createKey();
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 更新失效时间
    public static String updateJWT(String token) {
        SecretKey key = createKey();
        try{
            SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            claims.setIssuedAt(new Date());
            claims.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIME));
            JwtBuilder builder=Jwts.builder();
            builder.addClaims(claims);
            builder.signWith(algorithm,key);
            return builder.compact();
        }catch (Exception e){
            return null;
        }
    }

}
