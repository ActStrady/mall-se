package com.actstrady.service;

import com.actstrady.dao.UserDao;
import com.actstrady.pojo.User;

import java.util.Map;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 19:59
 * @fileName : UserService.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class UserService {
    /**
     * 最大错误登录次数
     */
    private static final int LOGIN_MAX = 3;
    /**
     * 当前登录user
     */
    private static User loginUser;

    /**
     * 登录业务
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功与否，以及账户类别
     */
    public String login(String username, String password) {
        UserDao userDao = new UserDao();
        User user = userDao.queryByUsernameAndPassword(username, password);
        loginUser = user;
        if (user == null) {
            return "failure";
        } else if (user.getType() == 0) {
            return "admin";
        } else {
            return "user";
        }
    }

    /**
     * 用户名密码失败三次，1分钟内不允许登录
     *
     * @param loginNumber 登录次数集合
     * @param username    用户名
     */
    public void loginOver(Map<String, Integer> loginNumber, String username) {
        Integer times = loginNumber.get(username);
        loginNumber.put(username, times + 1);
        if (times == LOGIN_MAX) {
            System.out.println("请一分钟后再次登录！");
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loginNumber.put(username, 0);
        }
    }

    /**
     * 获取当前登录user
     * @return 当前登录的user
     */
    public static User getLoginUser(){
        return loginUser;
    }
}
