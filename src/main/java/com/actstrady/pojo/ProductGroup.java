package com.actstrady.pojo;

import lombok.*;

import java.util.Date;

/**
 * 商品分类
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 20:39
 * @fileName : ProductGroup.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */

@Data
public class ProductGroup {
    private Integer id;
    private String name;
    private Integer creator;
    private String creatorName;
    private Boolean isDelete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
