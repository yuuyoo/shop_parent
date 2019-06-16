package com.djl.common.message.core.service;

import com.djl.common.message.core.entity.Message;
import com.djl.common.vo.R;
import org.springframework.stereotype.Service;

/**
 * @author DJL
 * @create 2019-06-14 22:06
 * @desc 消息服务层接口
 **/
@Service
public interface MessageService {


    /**
     * 分页查询消息发送的信息
     * @param page 第几页
     * @param count 每页查询数量
     * @return
     */
    R findByPage(Integer page, Integer count);

    /**
     * 检查手机号和验证码是否匹配
     * @param phone 手机号吗
     * @param code 验证码
     * @return
     */
    R checkCode(String phone, Integer code);

    /**
     * 发送消息入口
     * @param message 发送的消息
     * @param ip IP地址
     * @return
     */
    R sendMessage(Message message, String ip);
}
