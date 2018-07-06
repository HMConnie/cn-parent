package cn.connie.api.filter;


import cn.connie.api.wrapper.WebRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebRequestWrapperFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        WebRequestWrapper webRequestWrapper = new WebRequestWrapper(req);
        HttpServletResponse resp = (HttpServletResponse) response;
        chain.doFilter(webRequestWrapper, resp);
    }

    public void destroy() {

    }

}
