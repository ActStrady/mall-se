package com.actstrady.ui;

import com.actstrady.pojo.Product;
import com.actstrady.pojo.ProductGroup;
import com.actstrady.pojo.User;
import com.actstrady.service.ProductGroupService;
import com.actstrady.service.ProductService;
import com.actstrady.service.UserService;
import com.actstrady.utils.CommonUtils;

import java.math.BigDecimal;
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

    /**
     * 初始化界面
     */
    public void init() {
        // 管理员界面
        if ("admin".equals(type)) {
            System.out.println("管理员" + name + "欢迎登录喵喵喵商城管理系统！");
            while (true) {
                System.out.println("请选择您要的执行的操作");
                System.out.println("【1】录入商品分类【2】录入商品 【3】退出");
                String index = scanner.nextLine();
                switch (index) {
                    case "1":
                        entryProductGroup();
                        break;
                    case "2":
                        entryProduct();
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

    private void entryProduct() {
        ProductService productService = new ProductService();
        List<Product> products = productService.queryAllProduct();
        // 插入前查看
        CommonUtils.printList(products);
        // 插入商品分类
        System.out.println("请输入商品名称");
        String productName = scanner.nextLine();
        System.out.println("请输入商品价格");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("请输入商品品牌");
        String brand = scanner.nextLine();
        System.out.println("请输入商品库存");
        // TODO 类型转化异常
        Integer stock = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入商品状态");
        Integer state = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入商品分类");
        String productGroupName = scanner.nextLine();
        // 查询商品分类id
        Integer productGroup = new ProductGroupService().queryGroup(productGroupName).getId();
        for (Product productByName : products) {
            if (productByName.getName().equals(productName)) {
                System.out.println("商品已经存在，请重新输入");
                break;
            } else {
                Product product = new Product();
                product.setName(productName);
                product.setPrice(price);
                product.setBrand(brand);
                product.setStock(stock);
                product.setState(state);
                product.setProductGroup(productGroup);
                product.setCreator(loginUser.getId());
                product.setCreatorName(loginUser.getName());
                int result = productService.enterProduct(product);
                if (result > 0) {
                    System.out.println("录入成功");
                    // 显示成功的商品分类
                    CommonUtils.printList(productService.queryAllProduct());
                } else {
                    System.out.println("系统错误，请再试一次并联系管理员！");
                }
                break;
            }
        }
    }

    public void entryProductGroup() {
        ProductGroupService productGroupService = new ProductGroupService();
        List<ProductGroup> productGroups = productGroupService.queryAllGroup();
        // 插入前查看
        CommonUtils.printList(productGroups);
        // 插入商品分类
        System.out.println("请输入商品分类名称");
        String groupName = scanner.nextLine();
        for (ProductGroup productGroupByName : productGroups) {
            String name = productGroupByName.getName();
            if (name.equals(groupName)) {
                System.out.println("商品分类已经存在，请重新输入");
                break;
            } else {
                ProductGroup productGroup = new ProductGroup();
                productGroup.setName(groupName);
                productGroup.setCreator(loginUser.getId());
                productGroup.setCreatorName(loginUser.getName());
                int result = productGroupService.enterGroup(productGroup);
                if (result > 0) {
                    System.out.println("录入成功");
                    // 显示成功的商品分类
                    CommonUtils.printList(productGroupService.queryAllGroup());
                } else {
                    System.out.println("系统错误，请再试一次并联系管理员！");
                }
                break;
            }
        }
    }
}
