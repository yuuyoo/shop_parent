package com.djl.common.exception;

/**
 * 自定义用户操作异常
 */
public class UserException extends Exception {
    public UserException(){

    }
    public UserException(String msg){
        super(msg);
    }
}
