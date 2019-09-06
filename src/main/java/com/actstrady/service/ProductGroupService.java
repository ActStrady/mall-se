package com.actstrady.service;

import com.actstrady.dao.ProductGroupDao;
import com.actstrady.dao.UserDao;
import com.actstrady.pojo.ProductGroup;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 19:38
 * @fileName : ProductGroupService.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductGroupService {
    private ProductGroupDao productGroupDao = new ProductGroupDao();

    /**
     * 录入商品分类
     *
     * @param productGroup 商品分类
     * @return 是否成功
     */
    public int enterGroup(ProductGroup productGroup) {
        return productGroupDao.insert(productGroup);
    }

    /**
     * 查询商品分类列表
     *
     * @return 商品分类列表
     */
    public List<ProductGroup> queryAllGroup() {
        return productGroupDao.queryAll();
    }

    /**
     *  根据商品分类名查询商品分类id
     * @param name 商品分类名
     * @return 商品分类
     */
    public ProductGroup queryGroup(String name) {
        return productGroupDao.queryByName(name);
    }
}
