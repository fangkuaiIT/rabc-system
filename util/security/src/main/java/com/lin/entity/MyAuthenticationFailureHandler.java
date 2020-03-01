package com.lin.entity;




import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.http.HTTP;
import com.yyfly.common.util.JacksonMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败操作
 * @author K. L. Mao
 * @create 2019/1/15
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(HTTP.Status.UNAUTHORIZED.value());
        PrintWriter writer = httpServletResponse.getWriter();
        ResponseData data = ResponseData.build(HTTP.Status.UNAUTHORIZED);
        writer.write(JacksonMapper.getInstance().toJson(data));
        writer.close();
    }
}