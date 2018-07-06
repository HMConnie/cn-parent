package cn.connie.common.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalConfigration {

    private static final Map<String, String> CONFIG=new ConcurrentHashMap<String, String>();

    public static void reload(Properties prop) {
        for (String key: CONFIG.keySet()) {
            if (!prop.containsKey(key)) {
                CONFIG.remove(key);
            } else {
                String value=prop.getProperty(key);
                CONFIG.put(key, value);
            }
        }

        for (Object key: prop.keySet()) {
            String value=prop.getProperty(key.toString());
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (!CONFIG.containsKey(key.toString())) {
                CONFIG.put(key.toString(), value);
            }
        }
    }

    public static String toString(String key) {
        return CONFIG.get(key);
    }

    public static Long toLong(String key) {
        String value=toString(key);
        return StringUtils.isBlank(value) ? null : Long.valueOf(value);
    }

    public static Integer toInteger(String key) {
        String value=toString(key);
        return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
    }

    public static BigDecimal toBigDecimal(String key) {
        String value=toString(key);
        return StringUtils.isBlank(value) ? null : new BigDecimal(value);
    }
}
