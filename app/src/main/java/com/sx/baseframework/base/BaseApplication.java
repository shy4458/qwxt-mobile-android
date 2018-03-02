package com.sx.baseframework.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;

/**
 * @author JadynZhang
 * @version 1.0.0
 */
public class BaseApplication extends Application {
    public int count = 0;
    @SuppressLint("StaticFieldLeak")
    private static Context globalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = getApplicationContext();
        //前后台切换的判断
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (count == 0) {
                    Log.d("BaseApplication", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    Log.d("BaseApplication", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });


    }

    public static Context context() {
        return globalContext;
    }


    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static int get(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    public static long get(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    public static float get(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    public static boolean get(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public static String get(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public static SharedPreferences getPreferences() {
        return context().getSharedPreferences(AppConfig.PERF_NAME, Context.MODE_PRIVATE);
    }

}
