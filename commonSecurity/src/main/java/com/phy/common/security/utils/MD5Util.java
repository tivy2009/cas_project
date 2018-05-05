package com.phy.common.security.utils;

import java.security.MessageDigest;

/**
 * 
 * @desc: MD5加密工具
 * @author: tivy
 * @createTime: 2018-05-03 10:33:14
 * @history:
 * @version: v1.0
 */
public class MD5Util {

    // common encode salt string
    private static final String SALT = "yy";

    // specify encode salt string
    private static final String WECAHT_SALT = "yy_aa";

    /**
     * common encode method
     * 
     * @author: tivy
     * @createTime: 2018-05-03 10:35:47
     * @history:
     * @param password
     * @return String
     */
    public static String encode(String password) {
        password = password + SALT;
        return processEncode(password);
    }

    /**
     * specify encode method
     * 
     * @author: tivy
     * @createTime: 2018-05-03 10:35:21
     * @history:
     * @param password
     * @return String
     */
    public static String wechatEncode(String password) {
        password = password + WECAHT_SALT;
        return processEncode(password);
    }

    public static boolean wehcatValidation(String str, String token) {
        boolean flag = false;
        if (wechatEncode(str).equals(token)) {
            flag = true;
        }
        return flag;
    }

    public static String processEncode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("123456"));
        System.out.println(MD5Util.encode("admin"));
    }
}