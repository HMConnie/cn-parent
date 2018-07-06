package cn.connie.api.interceptor;

import cn.connie.common.exception.CustomException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadInterceptor extends HandlerInterceptorAdapter {
    private long maxSize;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CustomException {
        //判断是否文件上传
        if (request != null && ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext ctx = new ServletRequestContext(request);
            //获取上传文件尺寸大小
            long requestSize = ctx.contentLength();
            if (requestSize > maxSize) {
                //当上传文件大小超过指定大小限制后，模拟抛出MaxUploadSizeExceededException异常
                throw new CustomException("Maximum upload size of " + maxSize + " bytes exceeded", "最大上传文件不能超过" + maxSize + "比特");
            }
        }
        return true;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
