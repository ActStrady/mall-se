package com.actstrady.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:01
 * @fileName : User.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    /**
     * 用户类型：0 管理员， 1 普通用户
     */
    private Integer type;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
