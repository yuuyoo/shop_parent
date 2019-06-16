package com.djl.common.model;

import lombok.Data;

@Data
public class EmailMsg {
    //公司名称
    private String company;
    //邮箱标题
    private String title;
    //邮箱内容
    private String content;
    //接收人
    private String email;

}