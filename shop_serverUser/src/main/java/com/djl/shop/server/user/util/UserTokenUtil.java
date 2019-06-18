package com.djl.shop.server.user.util;

import com.alibaba.fastjson.JSON;
import com.djl.common.model.LoginToken;

/**
 * token解析工具类
 */
public class UserTokenUtil {

    /**
     * 根据token字符串解析出对象
     * @param json
     * @return
     */
    public static LoginToken parseToken(String json){
        if(json!=null){
            return JSON.parseObject(json,LoginToken.class);
        }else {
            return null;
        }
    }
    public static int parseTokenId(String json){
        if(json!=null){
            LoginToken user= JSON.parseObject(json,LoginToken.class);
            return user.getUid();
        }else {
            return 0;
        }
    }
}
