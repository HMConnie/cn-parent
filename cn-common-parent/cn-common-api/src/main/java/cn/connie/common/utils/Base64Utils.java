package cn.connie.common.utils;

import java.io.IOException;

/**
 * @Description: base64工具类
 * @Author: fengjing
 * @CreateDate: 2018/8/8$ 16:13$
 * @Version: 1.0
 */
public class Base64Utils {


    /**
     * 编码
     *
     * @param str
     * @return String
     */
    public static String encode(String str) {
        byte[] bytes = str.getBytes();
        return new sun.misc.BASE64Encoder().encode(bytes).replace("\n", "");
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static String decode(String str) {
        String s = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(str);
            s = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
