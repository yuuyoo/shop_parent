package com.djl.shop.server.user.dao;

import com.djl.shop.entity.UserSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSignMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 新增签到记录
     * @param record
     * @return
     */
    int insert(UserSign record);

    /**
     * 根据用户id查询所有相关的签到记录
     * @param uid 用户id
     * @return
     */
    List<UserSign> selectByUid(Integer uid);

    /**
     * 最近一次的签到记录
     * @param uid 用户id
     * @return
     */
    UserSign selectByUidLast(Integer uid);

    /**
     * 查询指定用户最近几天内的签到记录
     * @param uid 用户id
     * @param days 查询多少天内
     * @return
     */
    List<UserSign> selectByUidDays(@Param("uid") Integer uid, @Param("days") Integer days);

    int insertSelective(UserSign record);

    UserSign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSign record);

    int updateByPrimaryKey(UserSign record);
}