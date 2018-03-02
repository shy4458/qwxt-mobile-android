package com.sx.baseframework.util;

import android.text.TextUtils;

/**
 * Handling strings utility methods
 *
 * @author JadynZhang
 * @version 1.0.0
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    /**
     * Remove all white space characters in the given string.
     *
     * @param str String.
     * @return String that do not contain white space characters.
     */
    public static String toTrim(String str) {
        if(TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        char[] dest = new char[length];
        int destPosi = 0;
        char ch;
        for(int i = 0; i < length; i++) {
            ch = str.charAt(i);
            if(!Character.isWhitespace(ch)) {
                dest[destPosi++] = ch;
            }
        }
        return new String(dest, 0, destPosi);
    }

}
