package com.djl.common.message.core.dao;

import com.djl.common.message.core.entity.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 新增消息
     * @param record
     * @return
     */
    int insert(Message record);

    /**
     * 查询所有消息
     * @return
     */
    List<Message> selectAll();

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}