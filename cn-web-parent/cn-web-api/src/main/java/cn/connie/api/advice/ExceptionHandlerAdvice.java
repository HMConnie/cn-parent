package cn.connie.api.advice;

import cn.connie.common.exception.CustomException;
import cn.connie.common.exception.UnauthorizedException;
import cn.connie.common.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(CustomException e) {
        return ResponseUtils.toErrorResponse(e.getMessage(), e.getTips());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizedException e) {
        return ResponseUtils.toErrorResponse(e.getMessage(), "未获取到用户token令牌");
    }


}
