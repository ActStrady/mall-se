package com.actstrady.dao;

import com.actstrady.pojo.ProductGroup;
import com.actstrady.pojo.User;
import com.actstrady.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户dao
 *
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
        String sql = "select id, username, password, name, email, type, " +
                "is_delete, create_time, update_time, delete_time " +
                "from mall.user where username = ? and password= ?";
        return DbUtils.uniqQuery(User.class, sql, username, password);
    }
}
