package cn.connie.api.interceptor;

import cn.connie.api.wrapper.WebRequestWrapper;
import cn.connie.business.service.UserBusinessService;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.business.to.UserBTO;
import com.sgcai.commons.lang.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlineUserInterceptor implements HandlerInterceptor {

    @Resource
    private UserBusinessService userBusinessService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineUserInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        LOGGER.info("#####################进入onlineUser拦截器###############");

        WebRequestWrapper wrapper = getRequestWrapper(request);

        OnlineUserBTO onlineUser = new OnlineUserBTO();

        String accessToken = getAccessToken(wrapper);
        UserBTO user = userBusinessService.getUserByAccessToken(accessToken);
        if (user != null) {
            onlineUser.setUserId(user.getId());
            onlineUser.setAccessToken(accessToken);
            onlineUser.setMobile(user.getMobile());
            onlineUser.setNickname(user.getNickname());
            onlineUser.setPortrait(user.getHeadPortrait());
        }
        wrapper.setPrincipal(onlineUser);
        return true;
    }


    private WebRequestWrapper getRequestWrapper(HttpServletRequest request) {
        if (request instanceof DefaultMultipartHttpServletRequest) {
            DefaultMultipartHttpServletRequest multipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
            HttpServletRequest req = multipartHttpServletRequest.getRequest();
            return (WebRequestWrapper) req;
        }
        return (WebRequestWrapper) request;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    private String getAccessToken(WebRequestWrapper wrapper) {
        String authorization = wrapper.getHeader("Authorization");
        String accessToken;
        String tokenType;
        if (StringUtils.isBlank(authorization)) {
            accessToken = WebUtils.getCookieValue(wrapper, "accessToken");
            return accessToken;
        }

        String[] authorizations = authorization.split(" ");
        tokenType = authorizations[0];
        accessToken = authorizations[1];
        switch (tokenType) {
            case "common":
                return accessToken;

            default:
                return null;
        }

    }

}
