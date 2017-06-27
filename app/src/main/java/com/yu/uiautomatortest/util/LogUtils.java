package com.yu.uiautomatortest.util;

import android.util.Log;

public final class LogUtils {

    //私有构造 不允许实例化
    private LogUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 日志级别，如果设置为 ERROR 则不再输出日志
     */
    private static final int DEBUG_LEVEL = Log.VERBOSE;

    /**
     * VERBOSE_LEVER log message.
     */
    public static void v(Object obj) {

        if (Log.VERBOSE > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.v(tag, msg);
        }
    }

    /**
     * DEBUG_LEVEL log message.
     */
    public static void d(Object obj) {
        if (Log.DEBUG > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.d(tag, msg);
        }
    }

    /**
     * INFO_LEVER log message.
     */
    public static void i(Object obj) {
        if (Log.INFO > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.i(tag, msg);
        }
    }

    /**
     * WARN_LEVER log message.
     */
    public static void w(Object obj) {
        if (Log.WARN > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.w(tag, msg);
        }
    }

    /**
     * ERROR_LEVER log message.
     */
    public static void e(Object obj) {
        if (Log.ERROR > DEBUG_LEVEL) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.e(tag, msg);
        }
    }


    /**
     * VERBOSE_LEVER log message.
     *
     * @param tag TAG
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (Log.VERBOSE > DEBUG_LEVEL) {
            Log.v(tag, msg);
        }
    }

    /**
     * DEBUG_LEVEL log message.
     *
     * @param tag TAG
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (Log.DEBUG > DEBUG_LEVEL) {
            Log.d(tag, msg);
        }
    }

    /**
     * INFO_LEVER log message.
     *
     * @param tag TAG
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (Log.INFO > DEBUG_LEVEL) {
            Log.i(tag, msg);
        }
    }

    /**
     * WARN_LEVER log message.
     *
     * @param tag TAG
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (Log.WARN > DEBUG_LEVEL) {
            Log.w(tag, msg);
        }
    }

    /**
     * ERROR_LEVER log message.
     *
     * @param tag TAG
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (Log.ERROR > DEBUG_LEVEL) {
            Log.e(tag, msg);
        }
    }

    private static String getClassName() {
        String result = "";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        return result;
    }

}
