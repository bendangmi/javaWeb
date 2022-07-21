package com.atguigu.myssm.util;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/19-14:54
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
