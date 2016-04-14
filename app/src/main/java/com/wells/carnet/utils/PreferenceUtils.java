package com.wells.carnet.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences工具类
 *
 * @author wangdd
 * @date 2015-2-1
 */
public class PreferenceUtils {

    public static class Key {
        public static final String LOGIN_STATE = "LOGIN";
        public static final String OPEN_STATE = "FIRST_IN";
    }

    public static final String DEFAULT_STRING = "";
    public static final int DEFAULT_INT = 0;
    public static final boolean DEFAULT_BOOLEAN = false;
    public static final long DEFAULT_LONG = 0;
    public static final float DEFAULT_FLOAT = 0.0f;


    public static String getString(Context context, String key) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getString(key, DEFAULT_STRING);
    }

    public static String getString(Context context, String key, String defaultvalue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getString(key, defaultvalue);
    }

    public static void putString(Context context, final String key,
                                 final String value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getBoolean(Context context, final String key) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getBoolean(key, DEFAULT_BOOLEAN);
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key);
    }

    public static void putBoolean(Context context, final String key,
                                  final boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void putInt(Context context, final String key,
                              final int value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, final String key) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getInt(key, DEFAULT_INT);
    }

    public static void putFloat(Context context, final String key,
                                final float value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putFloat(key, value).commit();
    }

    public static float getFloat(Context context, final String key) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getFloat(key, DEFAULT_FLOAT);
    }

    public static void putLong(Context context, final String key,
                               final long value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, final String key) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getLong(key, DEFAULT_LONG);
    }

    public static void clearPreference(Context context,
                                       final SharedPreferences p) {
        final Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static void clearPreference(Context context) {
        final Editor editor = getDefaultSp(context).edit();
        editor.clear();
        editor.commit();
    }

    public static SharedPreferences getDefaultSp(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
