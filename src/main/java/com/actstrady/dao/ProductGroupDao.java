package com.actstrady.dao;

import com.actstrady.pojo.ProductGroup;
import com.actstrady.utils.DbUtils;

import java.util.List;

/**
 * 商品分类dao
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 23:49
 * @fileName : ProductGroupDao.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class ProductGroupDao {
    /**
     * 插入值
     *
     * @param productGroup 实体类
     * @return 非0成功
     */
    public int insert(ProductGroup productGroup) {
        String sql = "insert into mall.product_group (name, creator, creator_name) values (?, ?, ?);";
        return DbUtils.insert(productGroup, sql);
    }

    /**
     * 查询全部
     *
     * @return list
     */
    public List<ProductGroup> queryAll() {
        String sql = "select id, name, creator, creator_name, is_delete, create_time, update_time, delete_time "
                + "from mall.product_group;";
        return DbUtils.query(ProductGroup.class, sql);
    }

    /**
     * 通过name查id
     *
     * @param name 商品分类名
     * @return 实体类
     */
    public ProductGroup queryByName(String name) {
        String sql = "select * from mall.product_group where name = ?";
        return DbUtils.uniqQuery(ProductGroup.class, sql, name);
    }
}
