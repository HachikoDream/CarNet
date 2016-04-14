package com.wells.carnet.utils;

/**
 * Created by Wells on 2016/4/12.
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
