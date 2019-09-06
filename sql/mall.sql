# TODO creator_name算一个冗余字段，后期看查询是否频繁决定去留
# 创建数据库
drop database if exists mall;
create database mall;

# 用户表
drop table if exists mall.user;
create table mall.user
(
    id          int primary key auto_increment comment '主键',
    username    varchar(20) unique not null comment '用户名',
    password    varchar(20)        not null comment '密码',
    name        varchar(10)        not null comment '真实姓名',
    email       varchar(20) unique comment '邮箱',
    type        int      default 1 comment '用户类型0管理员 1普通用户',
    is_delete   bit      default 0 comment '是否删除',
    create_time datetime default current_timestamp comment '创建的时间',
    update_time datetime default current_timestamp on update current_timestamp comment '更新自动更新时间',
    delete_time datetime comment '删除时间'
) comment '用户表';
# 插入数据
insert into mall.user(username, password, name, email, type)
values ('admin', 'admin', '张三', 'act@act.com', 0);
insert into mall.user(username, password, name, email, type)
values ('super', '147258', '张四', 'sys@act.com', 1);
insert into mall.user(username, password, name, email, type)
values ('tom', '147258', '王五', 'wangwu@act.com', 1);
insert into mall.user(username, password, name, email, type)
values ('jerry', '147258', '刘萌', 'liumeng@act.com', 1);
insert into mall.user(username, password, name, email, type)
values ('hello', '147258', '王鬼', 'swanggui@act.com', 1);
insert into mall.user(username, password, name, email, type)
values ('hi', '147258', '李冰', 'libing@act.com', 1);
insert into mall.user(username, password, name, email, type)
values ('yellow', '147258', '张四', 'zhang@act.com', 1);

# 商品类别表
drop table if exists mall.product_group;
create table mall.product_group
(
    id           int primary key auto_increment comment '主键',
    name         varchar(20) unique not null comment '名称',
    creator      int                not null comment '创建者id',
    creator_name varchar(20) comment '创建者名',
    is_delete    bit      default 0 comment '是否删除',
    create_time  datetime default current_timestamp comment '创建的时间',
    update_time  datetime default current_timestamp on update current_timestamp comment '更新自动更新时间',
    delete_time  datetime comment '删除时间'
) comment '商品类别表';
# 插入数据
insert into mall.product_group(name, creator, creator_name)
values ('男装', 1, '张三');
insert into mall.product_group(name, creator, creator_name)
values ('女装', 1, '张三');
insert into mall.product_group(name, creator, creator_name)
values ('数码', 1, '张三');
insert into mall.product_group(name, creator, creator_name)
values ('文娱', 1, '张三');

# 商品类别表外键
alter table mall.product_group
    add constraint product_group_fk_creator
        foreign key (creator) references mall.user (id);

# 商品表
drop table if exists mall.product;
create table mall.product
(
    id            int primary key auto_increment comment '主键',
    name          varchar(255) not null comment '商品名',
    price         decimal      not null comment '价格',
    brand         varchar(20) comment '品牌',
    stock         int comment '库存',
    state         int      default 1 comment '状态0下架，1正常，2无货，3缺货',
    product_group int          not null comment '商品类别',
    creator       int          not null comment '创建者id',
    creator_name  varchar(20) comment '创建者名',
    is_delete     bit      default 0 comment '是否删除',
    create_time   datetime default current_timestamp comment '创建的时间',
    update_time   datetime default current_timestamp on update current_timestamp comment '更新自动更新时间',
    delete_time   datetime comment '删除时间'
) comment '商品表';
# 插入
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('男士运动鞋', 99.9, '李宁', 20, 1, 1, 1, 'admin');
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('男士休闲衣', 49.9, '以纯', 10, 1, 1, 1, 'admin');
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('女士运动裤', 199.9, '耐克', 2, 3, 2, 1, 'admin');
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('智能手机', 2999, '华为', 0, 2, 3, 1, 'admin');
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('java编程思想', 120.9, '华章科技', 29, 0, 4, 1, 'admin');
insert into mall.product(name, price, brand, stock, state, product_group, creator, creator_name)
values ('python数据分析', 98.8, '图灵图书', 20, 1, 4, 1, 'admin');

# 商品表外键
alter table mall.product
    add constraint product_fk_product_group
        foreign key (product_group) references mall.product_group (id);
alter table mall.product
    add constraint product_fk_creator
        foreign key (creator) references mall.user (id);

# 地址表
drop table if exists mall.address;
create table mall.address
(
    id                int primary key auto_increment comment '主键',
    creator           int          not null comment '创建者id, 标识是谁的地址',
    consignee         varchar(10)  not null comment '收货人',
    consignee_address varchar(100) not null comment '收货地址',
    consignee_phone   varchar(11)  not null comment '收货电话',
    is_delete         bit      default 0 comment '是否删除',
    create_time       datetime default current_timestamp comment '创建的时间',
    update_time       datetime default current_timestamp on update current_timestamp comment '更新自动更新时间',
    delete_time       datetime comment '删除时间'
) comment '地址表';

# 地址表外键
alter table mall.address
    add constraint address_fk_creator
        foreign key (creator) references mall.user (id);

# 订单表
drop table if exists mall.order;
create table mall.order
(
    id           int primary key auto_increment comment '主键',
    order_no     varchar(100) not null unique comment '订单号',
    state        int      default 1 comment '状态：0订单关闭1未付款2未发货3未确认收货4未评价5订单完',
    address      int          not null comment '地址',
    creator      int          not null comment '创建者id',
    creator_name varchar(20) comment '创建者名',
    is_delete    bit      default 0 comment '是否删除',
    create_time  datetime default current_timestamp comment '创建的时间',
    update_time  datetime default current_timestamp on update current_timestamp comment '自动更新时间',
    delete_time  datetime comment '删除时间'
) comment '订单表';

# 订单表外键
alter table mall.order
    add constraint order_fk_address
        foreign key (address) references mall.address (id);

# 订单详情表
drop table if exists mall.order_detail;
create table mall.order_detail
(
    product_id  int not null comment '商品编号',
    order_id    int not null comment '订单编号',
    amount      int not null comment '数量',
    is_delete   bit      default 0 comment '是否删除',
    create_time datetime default current_timestamp comment '创建的时间',
    update_time datetime default current_timestamp on update current_timestamp comment '更新自动更新时间',
    delete_time datetime comment '删除时间'
) comment '订单详情表';

# 订单详情外键
alter table mall.order_detail
    add constraint order_detail_fk_product_id
        foreign key (product_id) references mall.product (id);
alter table mall.order_detail
    add constraint order_detail_fk_order_id
        foreign key (order_id) references mall.order (id);