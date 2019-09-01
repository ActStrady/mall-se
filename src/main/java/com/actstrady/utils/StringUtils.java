package com.actstrady.utils;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author : ActStrady@tom.com
 * @date : 2019/8/28 11:43
 * @fileName : StringUtils.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class StringUtils {
    /**
     * 首字母大写
     *
     * @param name 转化的字符
     * @return 首字母转换为大写
     */
    public static String toUpperCaseFirst(String name) {
        char[] chars = name.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }

    /**
     * 首字母小写
     *
     * @param name 转化的字符
     * @return 首字母转换为大写
     */
    public static String toLowerCaseFirst(String name) {
        char[] chars = name.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

    /**
     * 驼峰命名转化为下划线命名
     *
     * @param name 驼峰命名
     * @return 下划线命名
     */
    public String humpToUnderLine(String name) {
        return name.replaceAll("[A-Z]+", "_$0").toLowerCase();
    }

    public String underLineToHump(String name) {
        return name.replaceAll("(-[a-z])+", "$0");
    }
}
