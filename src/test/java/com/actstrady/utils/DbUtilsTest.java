package com.actstrady.utils;

import com.actstrady.pojo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DbUtilsTest {

    @Test
    public void uniqQuery() {
        List<Object> list = new ArrayList<>();
        list.add("admin");
        User user = DbUtils.uniqQuery(User.class, "select * from mall.user where username=?", list);
        System.out.println(user);
    }
}