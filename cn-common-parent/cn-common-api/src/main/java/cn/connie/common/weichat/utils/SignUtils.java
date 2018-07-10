package cn.connie.common.weichat.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class SignUtils {
    private static String KEY = "85904555d60147b0aaf5d36991214bff";

    public static String sign(Map<String, String> sorts) {
        SortedMap<String, String> sort = new TreeMap<String, String>(sorts);
        String sign = "";
        Set<Map.Entry<String, String>> entries = sort.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            sign = sign + next.getKey() + "=" + next.getValue() + "&";
        }
        sign = sign + "key=" + KEY;
        sign = DigestUtils.md5Hex(sign.getBytes()).toUpperCase();
        return sign;
    }

    public static String xml(Map<String, String> sorts) {
        Set<Map.Entry<String, String>> entries = sorts.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuffer sb = new StringBuffer("<xml>");
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            sb.append("<");
            sb.append(next.getKey());
            sb.append(">");
            sb.append(next.getValue());
            sb.append("</");
            sb.append(next.getKey());
            sb.append(">");
        }
        sb.append("</xml>");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
