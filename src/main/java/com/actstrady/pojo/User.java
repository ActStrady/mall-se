package com.actstrady.pojo;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:01
 * @fileName : User.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    /**
     * 用户类型：0 管理员， 1 普通用户
     */
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
