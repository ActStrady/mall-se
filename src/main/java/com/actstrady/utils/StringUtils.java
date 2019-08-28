package com.actstrady.utils;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/8/28 11:43
 * @fileName : StringUtils.java
 * @gitHub : https://github.com/ActStrady/mall-se
 */
public class StringUtils {
    public static String toUpperCaseFirst(String name) {
        char[] chars = name.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }
}
