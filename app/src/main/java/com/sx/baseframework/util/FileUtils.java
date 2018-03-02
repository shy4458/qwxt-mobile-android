package com.sx.baseframework.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;

/**
 * File operation、storage utility methods.
 *
 * @author JadynZhang
 * @version 1.0.0
 */
public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    /**
     * Return the cache dir for external storage (/sdcard/Android/data/&lt;application package&gt;/cache). <br>
     * If the phone doesn't have SD card,return the cache dir for internal storage
     * (/data/data/&lt;application package&gt;/cache).
     *
     * @param context context
     * @return the cache dir
     */
    public static File getDiskCacheDir(@NonNull Context context) {
        File cachePath;
        if(externalStorageUseable()) {
            cachePath = context.getExternalCacheDir();
        } else {
            cachePath = context.getCacheDir();
        }
        return cachePath;
    }

    /**
     * Check external storage is useable.
     *
     * @return If external storage is mounted and not removable, return true. Otherwise return false.
     */
    public static boolean externalStorageUseable() {
        return (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable());
    }

    /**
     * Returns the file suffix. If <code>filePath</code> is null or empty string, returns a empty string.
     *
     * @param filePath File path.
     * @return The file suffix.
     */
    public static String getSuffix(String filePath) {
        if(TextUtils.isEmpty(filePath)) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
    }

    /**
     * Returns the file suffix. If the <code>file</code> is null or not exists or not a file,
     * returns a empty string.
     *
     * @param file File
     * @return The file suffix.
     */
    public static String getSuffix(File file) {
        if(null == file || !file.exists() || !file.isFile()) {
            return "";
        }
        return getSuffix(file.getAbsolutePath());
    }

}
