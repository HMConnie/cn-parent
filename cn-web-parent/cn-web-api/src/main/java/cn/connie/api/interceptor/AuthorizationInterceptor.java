package cn.connie.api.interceptor;

import cn.connie.api.wrapper.WebRequestWrapper;
import cn.connie.business.to.OnlineUserBTO;
import cn.connie.common.annotation.NeedLogin;
import cn.connie.common.exception.UnauthorizedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedException {

        HandlerMethod handlerMethod=(HandlerMethod)handler;

        NeedLogin needLogin=handlerMethod.getMethodAnnotation(NeedLogin.class);
        if (needLogin == null) {
            return true;
        }

        WebRequestWrapper wrapper=(WebRequestWrapper)request;
        OnlineUserBTO user=(OnlineUserBTO)wrapper.getUserPrincipal();
        if (!user.isLogined()) {
            throw new UnauthorizedException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
