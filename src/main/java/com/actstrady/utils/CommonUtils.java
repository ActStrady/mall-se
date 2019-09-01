package com.actstrady.utils;

import java.util.List;

/**
 * 常用的工具类
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/30 21:07
 * @fileName : CommonUtils.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class CommonUtils {
    /**
     * 以特定格式打印list
     *
     * @param list 要打印的List
     */
    public static void printList(List list) {
        System.out.println("-------------------------------------------");
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println("-------------------------------------------");
    }
}
