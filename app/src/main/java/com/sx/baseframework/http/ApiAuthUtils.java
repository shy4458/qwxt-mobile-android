package com.sx.baseframework.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.sx.baseframework.base.BaseApplication;

public final class ApiAuthUtils {

    private static final String KEY_ACCESS_TOKEN = "Sx_access_token";
    private static final String KEY_REFRESH_TOKEN = "Sx_refresh_token";
    private static final String KEY_APP_KEY = "appKey";
    private static final String KEY_APP_SECRET = "appSecret";
    private static String appKey;
    private static ApplicationInfo applicationInfo;

    static {
        Context context = BaseApplication.context();
        try {
            applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            appKey = applicationInfo.metaData.getString(KEY_APP_KEY);
            if(appKey == null || "".equals(appKey)) {
                throw new NullPointerException("Appkey is null.");
            }
        } catch (PackageManager.NameNotFoundException ignore) {}
    }

    private ApiAuthUtils() {}

    /**
     * Returns app key.
     *
     * @return App key.
     */
    public static String getAppKey() {
        return appKey;
    }

    /**
     * Returns app secret.
     *
     * @return App secret.
     */
    public static String getAppSecret() {
        return applicationInfo.metaData.getString(KEY_APP_SECRET);
    }

    /**
     * Returns access token. If SharedPreferences does not exist access token, returns empty string.
     *
     * @return Access token.
     */
    public static String getAccessToken() {
        return BaseApplication.get(KEY_ACCESS_TOKEN, "");
    }

    /**
     * Returns refresh token. If SharedPreferences does not exist refresh token, returns empty string.
     *
     * @return Refresh token.
     */
    public static String getRefreshToken() {
        return BaseApplication.get(KEY_REFRESH_TOKEN, "");
    }

    /**
     * Saves access token to SharedPreferences.
     *
     * @param accessToken token
     */
    public static void saveAccessToken(String accessToken) {
        BaseApplication.set(KEY_ACCESS_TOKEN, accessToken);
    }

    /**
     * Saves refresh token to SharedPreferences.
     *
     * @param refreshToken
     */
    public static void saveRefreshToken(String refreshToken) {
        BaseApplication.set(KEY_REFRESH_TOKEN, refreshToken);
    }

    /**
     * Saves token.Access token and refresh token.
     *
     * @param accessToken
     * @param refreshToken
     */
    public static void saveToken(String accessToken, String refreshToken) {
        SharedPreferences.Editor editor = BaseApplication.getPreferences().edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_REFRESH_TOKEN, refreshToken);
        editor.apply();
    }

}
