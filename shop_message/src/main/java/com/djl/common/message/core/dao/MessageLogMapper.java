package com.djl.common.message.core.dao;


import com.djl.common.message.core.entity.MessageLog;

public interface MessageLogMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 发送消息日志
     * @param record
     * @return
     */
    int insert(MessageLog record);

    int insertSelective(MessageLog record);

    MessageLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageLog record);

    int updateByPrimaryKey(MessageLog record);
}