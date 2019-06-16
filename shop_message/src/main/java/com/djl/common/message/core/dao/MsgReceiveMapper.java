package com.djl.common.message.core.dao;

import com.djl.common.message.core.entity.MsgReceive;

public interface MsgReceiveMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 新增消息接收记录
     * @param record
     * @return
     */
    int insert(MsgReceive record);

    int insertSelective(MsgReceive record);

    MsgReceive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgReceive record);

    int updateByPrimaryKey(MsgReceive record);
}