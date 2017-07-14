package com.wtcrmandroid.utils;

import java.io.File;
import java.io.FileInputStream;

import Decoder.BASE64Encoder;

/**
 * Created by wt-pc on 2017/7/14.
 */

public class Base64 {
    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
}
