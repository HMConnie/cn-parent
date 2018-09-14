package cn.connie.common.utils;

import com.github.binarywang.java.emoji.EmojiConverter;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: 李想
 * Date: 2017/12/18
 * Time: 12:26
 * Description:
 */
public abstract class EmojiUtil {

    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    /**
     * 将emojiStr转为 带有表情的字符
     *
     * @param emojiStr
     * @return
     */
    public static String emojiConverterUnicodeStr(String emojiStr) {
        if(StringUtils.isBlank(emojiStr)){
            return  null;
        }
        String result = emojiConverter.toUnicode(emojiStr);
        return result;
    }

    /**
     * 带有表情的字符串转换为编码
     *
     * @param str
     * @return
     */
    public static String emojiConverterToAlias(String str) {
        if(StringUtils.isBlank(str)){
            return  null;
        }
        String result = emojiConverter.toAlias(str);
        return result;
    }
}
