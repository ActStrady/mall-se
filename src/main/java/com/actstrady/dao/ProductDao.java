package com.actstrady.dao;

import com.actstrady.pojo.Product;
import com.actstrady.utils.DbUtils;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/9/6 22:17
 * @fileName : ProductDao.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductDao {
    public int insertProduct(Product product) {
        String sql = "i";
        return DbUtils.insert(product, sql);
    }

    public List<Product> queryAll() {
        String sql = "";
        return DbUtils.query(Product.class, sql );
    }
}
