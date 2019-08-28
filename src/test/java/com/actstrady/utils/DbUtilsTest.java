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
}