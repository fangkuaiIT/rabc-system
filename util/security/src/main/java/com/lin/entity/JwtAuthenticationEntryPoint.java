package com.lin.entity;

import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.http.HTTP;
import com.yyfly.common.util.JacksonMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理
 * @author : fangkuaiIT
 * @version : 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HTTP.Status.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        ResponseData data = ResponseData.build(HTTP.Status.UNAUTHORIZED);
        writer.write(JacksonMapper.getInstance().toJson(data));
        writer.close();
    }
}
