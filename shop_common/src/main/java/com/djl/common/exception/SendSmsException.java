package com.djl.common.exception;

/**
 * @author DJL
 * @create 2019-06-15 20:22
 * @desc 自定义短信发送异常
 **/
public class SendSmsException extends Exception{

    public SendSmsException() {

    }

    public SendSmsException(String msg) {
        super(msg);
    }
}
