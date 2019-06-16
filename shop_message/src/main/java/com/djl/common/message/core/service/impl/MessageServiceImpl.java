package com.djl.common.message.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.djl.common.config.ProjectConfig;
import com.djl.common.message.core.dao.MessageLogMapper;
import com.djl.common.message.core.dao.MessageMapper;
import com.djl.common.message.core.dao.MsgReceiveMapper;
import com.djl.common.message.core.entity.Message;
import com.djl.common.message.core.entity.MessageLog;
import com.djl.common.message.core.entity.MsgReceive;
import com.djl.common.message.core.service.MessageService;
import com.djl.common.model.ActiveCode;
import com.djl.common.model.EmailMsg;
import com.djl.common.util.*;
import com.djl.common.vo.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DJL
 * @create 2019-06-14 22:15
 * @desc 消息服务接口实现类，完成消息发送功能
 **/
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired(required = false)
    private MessageMapper messageMapper;

    @Autowired(required = false)
    private MessageLogMapper messageLogMapper;

    @Autowired(required = false)
    private MsgReceiveMapper msgReceiveMapper;

    // 连接redis数据库的单例对象注入
    @Autowired(required = false)
    private JedisUtil jedisUtil;

    @Override
    public R findByPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        PageInfo<Message> pageInfo = new PageInfo<>(messageMapper.selectAll());

        return R.setOK("OK", pageInfo);
    }

    @Override
    public R checkCode(String phone, Integer code) {
        String key = ProjectConfig.SMSCODE + phone;
        if (jedisUtil.exists(key)) {
            String ret = jedisUtil.get(key);
            if (ret != null) {
                if (Integer.parseInt(ret) == code) {
                    return R.setOK("验证码正确");
                } else {
                    return R.setERROR("验证码输入错误");
                }
            }
        }
        return R.setERROR("验证码已过期");
    }

    /**
     * 短信发送逻辑
     * @param message 消息
     * @param ip
     * @return
     */
    private R sendSms(Message message, String ip) {
            String phone = message.getReceive();
            // 验证手机号码格式
            if (!phone.matches("^1[356789]\\d{9}$")) {
                return R.setERROR("手机号码格式错误");
            }

            // 验证码
            int code = 0;
            int count = 0;
            boolean saveCodeToRedis = false;

            // 手机号一天发送条数上限判断
            String countOfDay = jedisUtil.get(ProjectConfig.SMSPREDAY + phone);
            if (countOfDay != null && countOfDay.matches("[0-9]{1,2}")) {
                // 已发送条数
                count = Integer.parseInt(countOfDay);
                if (count >= 20) {
                    return R.setERROR("该手机号码已达到当天发送上限");
                }

            }

            // 手机号一分钟内发送条数限制
            String countOfMinute = jedisUtil.get(ProjectConfig.SMSPRELIMIT + phone);
            if (countOfMinute != null) {
                return R.setERROR("该手机号码已达到一分钟发送上限");
            }

            // 上次验证码是否失效
            String smsCode = jedisUtil.get(ProjectConfig.SMSCODE + phone);
            if (smsCode != null) {
                code = Integer.parseInt(smsCode);
            } else {
                code = CodeUtil.createNum(6);
                // 新生成的验证码保存到redis
                saveCodeToRedis = true;

            }

            // 发送短信
            String info = "发送短信验证码：" + code;
            SmsUtil.mobileQuery(phone, code);

            // redis更新发送记录
            jedisUtil.setex(ProjectConfig.SMSPRELIMIT + phone, 60, "60秒发送限制");
            jedisUtil.setex(ProjectConfig.SMSPREDAY + phone, TimeUtil.getLastSeconds(), (count + 1) + "");

            if (saveCodeToRedis) {
                jedisUtil.setex(ProjectConfig.SMSCODE + phone, 600, code + "");
            }

            // 保存发送记录
            save(message, info, ip);
            return R.setOK("OK");
    }


    /**
     * 邮箱发送逻辑
     * @param message 消息
     * @param ip
     * @return
     */
    private R sendEmail(Message message, String ip) {
            String toEmail = message.getReceive();
            // 验证邮箱地址
            if (!toEmail.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
                return R.setERROR("邮箱地址格式错误");
            }

            int code = CodeUtil.createNum(6);
            String info = "发送邮箱验证码：" + code;
            EmailMsg emailMsg = new EmailMsg();
            // 设置公司名称
            emailMsg.setCompany(ProjectConfig.projects.get(message.getPcode()));
            ActiveCode activeCode = new ActiveCode();
            // 设置收件箱地址
            activeCode.setEmail(toEmail);
            emailMsg.setEmail(toEmail);
            // 设置验证码
            activeCode.setCode(code);
            // 生成密文，需要发送用户邮箱的信息
            String encString = EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE, JSON.toJSONString(activeCode));
            // 生成激活url
            String url = ProjectConfig.ACTIVEURL + "?data=" + encString + "&email="
                    + EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE, "djl");
            emailMsg.setContent("欢迎注册：" + emailMsg.getCompany() + ",请点击激活链接，<a href=''>"
                    + url + "</a>");
            EmailUtil.sendEmail(emailMsg);
            save(message, info, ip);
            return R.setOK("OK", null);

    }

    /**
     * 消息发送完成后需要记录发送的信息
     * @param message
     * @param info
     * @param ip
     */
    private void save(Message message, String info, String ip) {
            // 记录消息发送信息
            messageMapper.insert(message);
            // 记录消息日志
            MessageLog log = new MessageLog();
            log.setMid(message.getId());
            log.setInfo(info);
            log.setIp(ip);
            messageLogMapper.insert(log);
            // 收件人记录
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setNo(message.getReceive());
            msgReceive.setFlag(message.getType() < 4 ? 1 : 2);
            msgReceiveMapper.insert(msgReceive);

    }

    @Override
    public R sendMessage(Message message, String ip) {
        // 判断发送类别
        if (message.getType() < 4) {
            // 发短信
            return sendSms(message, ip);
        } else {
            // 发邮箱
            return sendEmail(message, ip);
        }
    }
}
