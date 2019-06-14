package com.djl.common.message.core.dao;

import com.djl.common.message.core.entity.MsgReceive;

public interface MsgReceiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgReceive record);

    int insertSelective(MsgReceive record);

    MsgReceive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgReceive record);

    int updateByPrimaryKey(MsgReceive record);
}