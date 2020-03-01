package com.lin.util.exception;

import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.http.HTTP;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * 全局异常处理
 * @author : fangkuaiIT
 * @version : 1.0
 */
@Component
public class GlobalExceptionHandler implements com.yyfly.common.exception.GlobalExceptionHandler {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ResponseData exceptionHandler(Exception exception) {
        logger.error("程序异常：", exception);

        if (exception instanceof GlobalException){
            GlobalException e = (GlobalException) exception;
            return ResponseData.error(e.getCode(), e.getMessage());
        }
        if(exception instanceof BadCredentialsException ||exception instanceof UsernameNotFoundException){
            return ResponseData.error(403,"用户名或密码错误");
        }

        if (exception instanceof IllegalArgumentException){
            return ResponseData.error(HTTP.Status.ERROR.value(), exception.getMessage());
        }

        if (exception instanceof NoSuchElementException){
            return ResponseData.error(HTTP.Status.ERROR.value(), "数据中含有不存在的编号!");
        }

        if (exception instanceof ExpiredJwtException || exception instanceof MalformedJwtException || exception instanceof SignatureException){
            return ResponseData.error(HTTP.Status.FORBIDDEN.value(), HTTP.Status.FORBIDDEN.getReasonPhrase());
        }
        if(exception instanceof AccessDeniedException){
            return ResponseData.error(413,"权限不足");
        }
        return ResponseData.build(HTTP.Status.ERROR);
    }
}
