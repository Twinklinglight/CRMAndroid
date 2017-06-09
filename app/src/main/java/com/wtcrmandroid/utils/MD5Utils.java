package com.wtcrmandroid.utils;

import java.security.MessageDigest;

/**
 * Created by Mr-Zhang on 2016/3/12.
 */
public class MD5Utils {
    public static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btlnput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdlnst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdlnst.update(btlnput);
            // 获得密文
            byte[] md = mdlnst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0>>>4 & 0xf];
                str[k++] = hexDigits[byte0 &0xf];
            }
            return new String(str).substring(8,24);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    };
    /**
     * MD5加密取前八位
     *
     * @param s
     * @return
     */
    public static String _MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btlnput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdlnst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdlnst.update(btlnput);
            // 获得密文
            byte[] md = mdlnst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).substring(0, 8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    };
}
