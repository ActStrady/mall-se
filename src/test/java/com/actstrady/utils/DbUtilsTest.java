package com.actstrady.utils;

import com.actstrady.pojo.User;
import com.mysql.cj.jdbc.admin.MiniAdmin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DbUtilsTest {

    @Test
    public void uniqQuery() {
        User user = DbUtils.uniqQuery(User.class, "select * from mall.user where username=?", "admin");
        System.out.println(user);
    }

    @Test
    public void query() {
        List<User> list = DbUtils.query(User.class, "select * from mall.user");
        System.out.println(list);
    }

    @Test
    public void insert() {
        User user = new User("body", "123456", "李强", "adt@qq.com", 1);
        String sql = "insert into mall.user(username, password, name, email, type) values(?, ?, ?, ?, ?)";
        System.out.println(DbUtils.insert(user, sql));
    }

    @Test
    public void delete() {
        String sql = "delete from mall.user where id = ?";
        System.out.println(DbUtils.delete(sql, 7));
    }

    @Test
    public void update() {
        User user = new User("body", "123456", "孙强", "adt@qq.com", 1);
        String sql = "update mall.user set username = ?, password = ?, name =?, email = ?, type = ? " +
                "where id = ?";
        DbUtils.update(user, sql, 8);
        String sql2 = "update mall.user set password = ? where id = ?";
        DbUtils.updateByParams(sql2, "111111", 8);
    }
}