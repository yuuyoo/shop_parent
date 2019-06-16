package com.djl.common.exception;

/**
 * @author DJL
 * @create 2019-06-15 20:32
 * @desc 消息保存异常
 **/
public class MessageException extends Exception{
    public MessageException() {

    }

    public MessageException(String msg) {
        super(msg);
    }
}
