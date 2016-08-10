package org.chennl.demo.util;

import android.util.Log;

import org.chennl.demo.BuildConfig;

/**
 * 日志打印工具类
 * Created by chennl on 16-8-4.
 */
public class Logger {

    public static final String TAG = "Logger";

    public static void v(String tag, String msg) {
        if (BuildConfig.LOG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.LOG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.LOG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.LOG) {
            Log.e(tag, msg);
        }
    }

}
