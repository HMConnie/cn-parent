package cn.connie.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 密码加密和匹配
 */
public class MiscUtils {
    public static boolean matchPassword(String password, String passwordFormDB) {
        if (StringUtils.isBlank(passwordFormDB)) {
            return false;
        }
        if (passwordFormDB.equals(encodePassword(password))) {
            return true;
        }
        return false;
    }

    public static String encodePassword(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        return DigestUtils.md5Hex(password);
    }
}
