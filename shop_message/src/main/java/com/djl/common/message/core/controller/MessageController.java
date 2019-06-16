package com.djl.common.message.core.controller;

import com.djl.common.message.core.entity.Message;
import com.djl.common.message.core.service.MessageService;
import com.djl.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DJL
 * @create 2019-06-15 20:02
 * @desc 消息管理控制器
 **/
@Api(value = "统一消息服务平台", tags = "统一消息服务数据接口")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 分页查询消息发送的信息
     * @param page 第几页
     * @param count 每页查询数量
     * @return
     */
    @ApiOperation("分页查询消息发送内容的数据接口")
    @GetMapping("/message/page.do")
    R findByPage(@RequestParam("page") Integer page, @RequestParam("count") Integer count) {
        return  messageService.findByPage(page, count);
    }

    /**
     * 检查手机号和验证码是否匹配
     * @param phone 手机号吗
     * @param code 验证码
     * @return
     */
    @ApiOperation("手机号与验证码校验的数据接口")
    @GetMapping("/message/checkCode.do")
    R checkCode(@RequestParam("phone") String phone, @RequestParam("code") Integer code) {
        return messageService.checkCode(phone, code);
    }

    /**
     * 发送消息
     * @param message 发送的消息
     * @param request
     * @return
     */
    @ApiOperation("消息发送的数据接口平台")
    @PostMapping("/message/sendMsg.do")
    R sendMessage(@RequestBody Message message, HttpServletRequest request) {
        return messageService.sendMessage(message, request.getRemoteAddr());
    }
}
