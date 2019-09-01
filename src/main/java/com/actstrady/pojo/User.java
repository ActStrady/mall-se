package com.actstrady.pojo;

import java.util.Date;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:01
 * @fileName : User.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
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

    public User() {
    }

    public User(String username, String password, String name, String email, int type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}
