package com.actstrady.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author : ActStrady@tom.com
 * @date : 2019/9/6 20:56
 * @fileName : Products.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
@Data
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String brand;
    private Integer stock;
    private Integer state;
    private Integer productGroup;
    private Integer creator;
    private String creatorName;
    private String isDelete;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
