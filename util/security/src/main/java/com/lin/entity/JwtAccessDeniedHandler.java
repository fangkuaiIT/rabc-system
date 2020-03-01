package com.lin.entity;

import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.http.HTTP;
import com.yyfly.common.util.JacksonMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足处理
 * @author : fangkuaiIT
 * @version : 1.0
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HTTP.Status.FORBIDDEN.value());
        PrintWriter writer = response.getWriter();
        ResponseData data = ResponseData.build(HTTP.Status.FORBIDDEN);
        writer.write(JacksonMapper.getInstance().toJson(data));
        writer.close();
    }
}
