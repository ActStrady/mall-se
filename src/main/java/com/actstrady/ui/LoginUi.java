package com.actstrady.ui;

import com.actstrady.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 登录界面
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 19:51
 * @fileName : LoginUi.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class LoginUi {
    public static void main(String[] args) {
        System.out.println("欢迎使用喵喵喵商城");
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        Map<String, Integer> loginNumber = new HashMap<>(100);

        while (true) {
            // 登录验证
            System.out.println("请输用户名：");
            String username = scanner.nextLine();
            System.out.println("请输入密码：");
            String password = scanner.nextLine();
            String result = userService.login(username, password);

            // 处理登录错误三次
            if ("failure".equals(result)) {
                System.out.println("账号或密码输入错误！请重新输入。");
                // 使用putIfAbsent，只有没有的时候才插入
                loginNumber.putIfAbsent(username, 1);
                userService.loginOver(loginNumber, username);
            } else if (result != null) {
                new MenuUi(result, UserService.getLoginUser().getName()).init();
            } else {
                System.out.println("系统异常，请联系管理员！");
                System.exit(-1);
                break;
            }
        }
    }
}
