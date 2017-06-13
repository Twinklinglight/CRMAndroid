package com.wtcrmandroid.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Mr-Zhang on 2016/3/12.
 */
public class DESUtils {
    /**
     * 加密
     *
     * @param src
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     * */
    public static String encrypt(String src, String key)
            throws RuntimeException {
        try {
            // 从原始密匙数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            Cipher cipher_encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher_encrypt.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 加密
            return byte2hex(cipher_encrypt.doFinal(src.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     * */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
