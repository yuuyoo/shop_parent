package com.djl.common.model;

import lombok.Data;


/**
 * 验证码对象
 */
@Data
public class ActiveCode {
    private String email;
    private int code;

}
