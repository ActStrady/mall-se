package com.actstrady.dao;

import com.actstrady.pojo.ProductGroup;
import com.actstrady.utils.DbUtils;

import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 23:49
 * @fileName : ProductGroupDao.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductGroupDao {
    public int insertGroup(ProductGroup productGroup) {
        String sql = "insert into mall.product_group (name, creator, creator_name) values (?, ?, ?);";
        return DbUtils.insert(productGroup, sql);
    }

    public List<ProductGroup> queryAll() {
        String sql = "select id, name, creator, creator_name, is_delete, create_time, update_time, delete_time " +
                "from mall.product_group;";
        return DbUtils.query(ProductGroup.class, sql);
    }
}
