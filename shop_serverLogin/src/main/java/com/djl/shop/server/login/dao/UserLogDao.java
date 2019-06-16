package com.djl.shop.server.login.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author DJL
 * @create 2019-06-16 11:14
 * @desc 用户登录日志DAO接口
 **/
public interface UserLogDao {

    /**
     * 添加用户登录记录
     * @param uid 用户id
     * @param content 日志记录说明
     */
    @Insert("insert into userlog(uid, flag, content, createtime) values(#{uid}, 2, #{content}, now())")
    void save(@Param("uid") int uid, @Param("content") String content);
}
