package com.wdx.springbootshirojwt.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public String handleIndexOutOfBoundsException(Exception e) {
        return e.getMessage();
    }

    /**
     * 未授权异常
     * @param e
     * @return
     */
    @ExceptionHandler({AuthorizationException.class})
    public String handleAuthorizationException(AuthorizationException e) {
        return e.getMessage();
    }

}
