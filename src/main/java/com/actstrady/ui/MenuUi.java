package com.actstrady.ui;

import com.actstrady.pojo.ProductGroup;
import com.actstrady.pojo.User;
import com.actstrady.service.ProductGroupService;
import com.actstrady.service.UserService;
import com.actstrady.utils.CommonUtils;

import java.util.List;
import java.util.Scanner;

/**
 * 登录后菜单界面
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/27 11:30
 * @fileName : AdminUi.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class MenuUi {
    private String type;
    private String name;
    private Scanner scanner = new Scanner(System.in);
    private User loginUser = UserService.getLoginUser();

    MenuUi(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public void init() {
        if ("admin".equals(type)) {
            System.out.println("管理员" + name + "欢迎登录喵喵喵商城管理系统！");
            while (true) {
                System.out.println("请选择您要的执行的操作");
                System.out.println("【1】录入商品分类【2】录入商品 【3】退出");
                String index = scanner.nextLine();
                switch (index) {
                    case "1":
                        // 插入商品分类
                        ProductGroupService productGroupService = new ProductGroupService();
                        System.out.println("请输入商品分类名称");
                        String groupName = scanner.nextLine();
                        ProductGroup productGroup = new ProductGroup();
                        productGroup.setName(groupName);
                        productGroup.setCreator(loginUser.getId());
                        productGroup.setCreatorName(loginUser.getName());
                        int result = productGroupService.enterGroup(productGroup);
                        if (result > 0) {
                            System.out.println("录入成功");
                            // 显示成功的商品分类
                            List<ProductGroup> productGroups = productGroupService.queryAllGroup();
                            CommonUtils.printList(productGroups);
                        }
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
