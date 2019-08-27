package com.actstrady.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/26 20:09
 * @fileName : DBUtils.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class DbUtils {
    private static final String URL = "jdbc:mysql:///";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> T uniqQuery(Class<T> clazz, String sql, List<Object> params) {
        // 要注意判空
        T t = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // 动态创建对象
                t = clazz.newInstance();
                // 反射获取所有的域（变量）
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().contains("set")) {
                        // 获取域的名称
                        String fieldName =  method.getName().toLowerCase().substring(3);
                        // 执行方法（给新对象设置值）
                        method.invoke(t, resultSet.getObject(fieldName));
                    }
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

}
