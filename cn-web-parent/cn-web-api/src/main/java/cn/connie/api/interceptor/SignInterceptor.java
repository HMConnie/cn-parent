package cn.connie.api.interceptor;

import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.Base64Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SignInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignInterceptor.class);

    @Value("${SIGN_KEY}")
    private String SIGN_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CustomException, UnsupportedEncodingException {

        LOGGER.info("################进入签名拦截器##################");

        String sign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        String channelId = request.getHeader("channelId");

        // 时间戳校验
        if (System.currentTimeMillis() - Long.valueOf(timestamp) > 1000 * 60 * 24) {
            throw new CustomException("request expiration ", "请求超时");
        }

        // 验签
        Map<String, String> params = new HashMap<>();
        params.put("channelId", channelId);
        params.put("timestamp", timestamp);

        Map<String, String[]> businessParams = request.getParameterMap();

        Set<Entry<String, String[]>> set = businessParams.entrySet();
        // 循环遍历业务参数，去掉参数为空的param
        for (Entry<String, String[]> businessParam : set) {
            if (StringUtils.isNotBlank(businessParam.getValue()[0])) {
                params.put(businessParam.getKey(), businessParam.getValue()[0]);
            }
        }

        if (!verify(params, sign)) {
            throw new CustomException("sign error", "签名错误");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    /**
     * @param params
     * @param sign
     * @return
     * @throws UnsupportedEncodingException
     */
    private boolean verify(Map<String, String> params, String sign) throws UnsupportedEncodingException {

        TreeMap<String, String> map = new TreeMap<>(params);
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> param : map.entrySet()) {
            String base64Encode = Base64Utils.encode(param.getValue());
            sb.append(param.getKey()).append("=").append(base64Encode).append("&");
        }

        sb.append("key").append("=").append(SIGN_KEY);
        String localSign = DigestUtils.md5Hex(sb.toString());
        System.out.println("签名串：" + sb);
        System.out.println("签名：" + localSign);
        if (!sign.equals(localSign)) {
            return false;
        }

        return true;
    }

}
