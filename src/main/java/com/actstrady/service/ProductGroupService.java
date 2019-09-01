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

    public int enterGroup(ProductGroup productGroup) {
        return productGroupDao.insertGroup(productGroup);
    }

    public List<ProductGroup> queryAllGroup() {
        return productGroupDao.queryAll();
    }
}
