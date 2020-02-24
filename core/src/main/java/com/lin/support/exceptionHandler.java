package com.lin.support;


import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;

/**
 * 异常处理
 * @author : fangkauiIT
 * @version : 1.0
 */
public abstract class exceptionHandler {

    /**
     * 未知异常处理.
     *
     * @param e the e
     * @return the response data
     * @author : fangkauiIT/ 2019-04-13
     */
    public abstract ResponseData exceptionHandler(Exception e);

    /**
     * 已知异常处理.
     *
     * @param e the e
     * @return the response data
     * @author : fangkauiIT/ 2019-04-13
     */
    public abstract ResponseData globalExceptionHandler(GlobalException e);
}
