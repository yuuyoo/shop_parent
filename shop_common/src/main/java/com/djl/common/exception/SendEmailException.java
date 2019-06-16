package com.djl.common.exception;

/**
 * @author DJL
 * @create 2019-06-15 20:20
 * @desc 自定义邮箱发送异常
 **/
public class SendEmailException extends Exception {
    public SendEmailException() {

    }

    public SendEmailException(String msg) {
        super(msg);
    }
}
