package com.actstrady.dao;

import com.actstrady.pojo.User;
import com.actstrady.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:09
 * @fileName : UserDao.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class UserDao {
    /**
     * 根据账号密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户类
     */
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, name, email, type from mall.user " +
                "where username = ? and password= ?";
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        return DbUtils.uniqQuery(User.class, sql, params);
    }
}
