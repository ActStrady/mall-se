package com.actstrady.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * jdbc工具类
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:09
 * @fileName : DBUtils.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class DbUtils {
    /**
     * TODO 写入配置文件
     */
    private static final String URL = "jdbc:mysql:///";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static int result = 0;

    private static Pattern pattern = Pattern.compile("[A-Z]");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询单个
     *
     * @param clazz  类
     * @param sql    sql语句
     * @param params ...可变参数，和数组意义一样
     * @param <T>    泛型
     * @return 单个类
     */
    public static <T> T uniqQuery(Class<T> clazz, String sql, Object... params) {
        T t = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // 动态创建对象
                t = clazz.newInstance();
                // 反射获取所有方法
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().contains("set")) {
                        // 获取域的名称
                        String fieldName = StringUtils.toLowerCaseFirst(method.getName().substring(3));
                        Matcher matcher = pattern.matcher(fieldName);
                        if (matcher.find()) {
                            // 将驼峰转化为下划线
                            fieldName = fieldName.replaceAll("[A-Z]+", "_$0").
                        }
                        // 执行方法（给新对象设置值）
                        System.out.println(resultSet.getObject(fieldName));
                        method.invoke(t, resultSet.getObject(fieldName));
                    }
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 查询多个
     *
     * @param clazz  类型
     * @param sql    sql
     * @param params 查询条件
     * @param <T>    泛型
     * @return 查询数据列表
     */
    public static <T> List<T> query(Class<T> clazz, String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            // 动态创建对象
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                // 反射获取所有的域（变量）
                Field[] fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Method method =
                            clazz.getDeclaredMethod("set" + StringUtils.toUpperCaseFirst(fields[i].getName()),
                                    fields[i].getType());
                    method.invoke(t, resultSet.getObject(i + 1));
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 插入
     *
     * @param object 具体的一个javabean
     * @param sql    sql
     * @return 非0就是成功
     */
    public static int insert(Object object, String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 1; i < fields.length; i++) {
                Method method =
                        object.getClass().getDeclaredMethod("get" + StringUtils.toUpperCaseFirst(fields[i].getName()));
                if (method.invoke(object) != null) {
                    statement.setObject(i, method.invoke(object));
                }
            }
            result = statement.executeUpdate();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除
     *
     * @param sql    sql
     * @param params 条件
     * @return 非0就是成功
     */
    public static int delete(String sql, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int update(Object object, String sql, Object... params) {
        try {
            int i = 1;
            PreparedStatement statement = connection.prepareStatement(sql);
            Field[] fields = object.getClass().getDeclaredFields();
            for (; i < fields.length; i++) {
                Method method =
                        object.getClass().getDeclaredMethod("get" + StringUtils.toUpperCaseFirst(fields[i].getName()));
                statement.setObject(i, method.invoke(object));
            }
            for (Object param : params) {
                statement.setObject(i++, param);
            }
            result = statement.executeUpdate();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
