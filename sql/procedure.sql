# 存储过程

# 账户表
drop table if exists test.account;
create table test.account
(
    id          int primary key auto_increment comment 'id',
    account_num varchar(50) comment '账号',
    total       decimal(50, 3) comment '余额'
);
insert into test.account (account_num, total)
values ('1258', 200.256);
insert into test.account (account_num, total)
values ('1259', 500.266);

# 转账存储过程
drop procedure if exists test.transfer;
# 参数： 转入账号，转出账号
create procedure test.transfer(in_account int, out_account int, amount decimal)
# 存储过程没有返回，故设置一个标记
label_pro:
begin
    # 定义变量
    # 转出账户金额
    declare total_deposit_out decimal;
    # 转入账户金额
    declare total_deposit_in decimal;

    # 查询转出账户余额并且存入变量
    select total into total_deposit_out from test.account where account_num = out_account;
    # 转出账户不存在，回滚
    if total_deposit_out is null then
        rollback;
        leave label_pro;
    end if;

    # 查询转入账户余额并且存入变量
    select total into total_deposit_in from test.account where account_num = in_account;
    # 转入账户不存在，回滚
    if total_deposit_in is null then
        rollback;
        leave label_pro;
    end if;

    # 转出余额不足，回滚
    if total_deposit_out < amount then
        rollback;
        leave label_pro;
    end if;

    update test.account set total = total - amount where account_num = out_account;
    update test.account set total = total + amount where account_num = in_account;
    select * from test.account;
    commit;
end;

# 调用存储过程
call test.transfer(1258, 1259, 500);

# 转账存储过程
drop function if exists test.transfer;
# 参数： 转入账号，转出账号
create function test.transfer_fun(in_account int, out_account int, amount decimal)
# 存储过程没有返回，故设置一个标记
    returns int
begin
    # 定义变量
    # 转出账户金额
    declare total_deposit_out decimal;
    # 转入账户金额
    declare total_deposit_in decimal;

    # 查询转出账户余额并且存入变量
    select total into total_deposit_out from test.account where account_num = out_account;
    # 转出账户不存在，回滚
    if total_deposit_out is null then
        rollback;
        return -1;
    end if;

    # 查询转入账户余额并且存入变量
    select total into total_deposit_in from test.account where account_num = in_account;
    # 转入账户不存在，回滚
    if total_deposit_in is null then
        rollback;
        return -1;
    end if;

    # 转出余额不足，回滚
    if total_deposit_out < amount then
        rollback;
        return -1;
    end if;

    update test.account set total = total - amount where account_num = out_account;
    update test.account set total = total + amount where account_num = in_account;
    select * from test.account;
    return 1;
end;

# 调用函数
call test.transfer(1258, 1259, 500);