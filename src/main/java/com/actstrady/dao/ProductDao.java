package com.actstrady.dao;

import com.actstrady.pojo.Product;
import com.actstrady.utils.DbUtils;

import java.util.List;

/**
 * 商品dao
 *
 * @author : ActStrady@tom.com
 * @date : 2019/9/6 22:17
 * @fileName : ProductDao.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductDao {
    /**
     *  插入商品
     * @param product 商品
     * @return 成功与否
     */
    public int insertProduct(Product product) {
        String sql = "";
        return DbUtils.insert(product, sql);
    }

    /**
     *  查询所有商品
     * @return 商品列表
     */
    public List<Product> queryAll() {
        String sql = "";
        return DbUtils.query(Product.class, sql);
    }
}
