package com.djl.common.model;

import lombok.Data;

/**
 * token视图对象
 */
@Data
public class LoginToken {
    private String id;
    private String phone;
    private int uid;
}
