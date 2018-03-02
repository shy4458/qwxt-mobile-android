package com.sx.baseframework.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Data encryption utility methods.
 *
 * @author JadynZhang
 * @version 1.0.0
 */
public class SecurityUtils {

    private SecurityUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    private static final String TYPE_MD5 = "MD5";
    private static final String TYPE_HMAC_MD5 = "HmacMD5";

    /**
     * MD5 encryption algorithm.
     *
     * @param value Value before encryption.
     * @return Encrypted hex string.
     */
    public static String md5(String value) {
        if(value == null) {
            return "";
        }
        String result;
        try {
            final MessageDigest digest = MessageDigest.getInstance(TYPE_MD5);
            digest.update(value.getBytes());
            result = Hex.toHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            result = "";
        }
        return result;
    }

    /**
     * HmacMD5 message digest algorithm（消息摘要算法）.
     *
     * @param message The initial message
     * @param secretKey Secret key
     * @return  The message digest. Or empty string when an exception occurs.
     */
    public static String hmacMD5(String message, String secretKey) {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), TYPE_HMAC_MD5);
        try {
            Mac mac = Mac.getInstance(key.getAlgorithm());
            mac.init(key);
            byte[] bytes = mac.doFinal(message.getBytes());
            return Hex.toHexString(bytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return "";
        }
    }

}
