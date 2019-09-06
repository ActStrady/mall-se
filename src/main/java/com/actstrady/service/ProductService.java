package com.actstrady.service;

import com.actstrady.pojo.Product;

import java.util.List;

import com.actstrady.dao.ProductDao;

/**
 * 商品业务
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 19:38
 * @fileName : ProductService.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductService {

    private ProductDao productDao;

    /**
     * 录入商品信息
     *
     * @param product 商品
     * @return 成功与否
     */
    public int enterProduct(Product product) {
        return productDao.insertProduct(product);
    }

    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    public List<Product> queryAllProduct() {
        return productDao.queryAll();
    }
}
