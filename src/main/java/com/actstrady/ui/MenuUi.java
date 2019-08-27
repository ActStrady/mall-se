package com.actstrady.ui;

import java.util.Scanner;

/**
 * 登录后菜单界面
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/27 11:30
 * @fileName : AdminUi.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
class MenuUi {
    private String type;
    private String name;
    private Scanner scanner = new Scanner(System.in);

    MenuUi(String type, String name) {
        this.type = type;
        this.name = name;
    }

    void init() {
        if ("admin".equals(type)) {
            System.out.println("管理员" + name + "欢迎登录喵喵喵商城管理系统！");
            while (true) {
                System.out.println("请选择您要的执行的操作");
                System.out.println("【1】录入商品分类【2】录入商品 【3】退出");
                String index = scanner.nextLine();
                switch (index) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("请输入正确的选项！");
                }
            }
        }
    }
}
