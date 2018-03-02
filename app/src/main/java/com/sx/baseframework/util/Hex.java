package com.sx.baseframework.util;

import java.io.ByteArrayOutputStream;

public final class Hex {

    private Hex() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    /**
     * Converts binary byte array to a hex string.
     *
     * @param data Byte array.
     * @return A hex string.
     */
    public static String toHexString(byte[] data) {
        if(null == data || data.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = data.length;
        for(int i = 0; i < length; i++) {
            String hex = Integer.toHexString(0xFF & data[i]);
            if(hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Converts hex string to binary byte array.
     *
     * @param hexString The hex string
     * @return The binary byte array
     */
    public static byte[] toBinaryBytes(String hexString) {
        if(null == hexString || hexString.length() == 0) {
            return new byte[]{};
        }
        int length = hexString.length();
        if(length % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex string");
        }
        char[] chars = hexString.toCharArray();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for (int i = 0, j = 0; i < length; i++, j++) {
            String swap = "" + chars[i++] + chars[i];
            bOut.write((Integer.parseInt(swap, 16) & 0xFF));
        }
        return bOut.toByteArray();
    }

}
