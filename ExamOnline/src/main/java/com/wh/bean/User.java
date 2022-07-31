package com.wh.bean;

import lombok.Data;

/**
 * @author whstart
 * @creat 2022--02-15:22
 */
@Data
public class User {
    //学号
    private Integer userId;
    //姓名
    private String userName;
    //性别
    private String sex;
    //专业
    private String profession;
    //密码
    private String  password;
    //身份
    private String identity;
    //表示，区分身份
    private int flag;
}
