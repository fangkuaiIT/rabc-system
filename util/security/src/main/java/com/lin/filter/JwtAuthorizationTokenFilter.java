package com.lin.filter;




import com.lin.entity.JwtUser;
import com.lin.util.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt token认证过滤器
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthorizationTokenFilter.class);

    /**
     * User details service
     */
    private final UserDetailsService userDetailsService;
    /**
     * Jwt token utils
     */
    private final JwtTokenUtils jwtTokenUtils;

    /**
     * Jwt authorization token filter.
     *
     * @param userDetailsService the user details service
     * @param jwtTokenUtils      the jwt token utils
     */
    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtils jwtTokenUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("开始处理请求 : " + request.getRequestURL().toString());

        //解决预请求跨域
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())){
            response.setHeader("Access-Control-Allow-Origin", "*");  //可配置允许访问的请求方地址。这里配置成*号，是允许所有访问。
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE"); //运行所有 Method, GET, POST, PUT, DELETE...
            response.setHeader("Access-Control-Max-Age", "3600");    //1h
            response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,content-type,Accept,authorization,api-version");  //Authorization,api-version是我自定义的请求头认证，在这里必须添加，否则就会出跨域错误
            return;
        }
        else {
            String token = null;
            String userName = null;

            //头部是否有认证信息token
            token = request.getHeader(jwtTokenUtils.getHeader());
            if (StringUtils.isNotEmpty(token)){

                //验证token信息
                userName = jwtTokenUtils.getUserName(token);

                if (StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                    JwtUser jwtUser = (JwtUser) userDetails;
                    if (jwtTokenUtils.validateToken(token, jwtUser.getUsername(), jwtUser.getUser().getLastPasswordReset())){
                        //用户信息注册到security context holder中
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

        }

        filterChain.doFilter(request, response);
    }
}
