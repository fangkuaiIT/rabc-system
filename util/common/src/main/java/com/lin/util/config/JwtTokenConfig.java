package com.lin.util.config;


import com.lin.util.config.properties.JwtTokenProperties;
import com.lin.util.utils.JwtTokenUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JwtToken config
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Configuration
public class JwtTokenConfig {

    /**
     * Jwt token properties 配置类.
     *
     * @return the jwt token properties
     * @author : fangkuaiIt / 2019-01-09
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtTokenProperties jwtTokenProperties(){
        return new JwtTokenProperties();
    }

    /**
     * Jwt token utils类.
     *
     * @return the jwt token utils
     * @author : fangkuaiIt / 2019-01-09
     */
    @Bean
    public JwtTokenUtils jwtTokenUtils(){
        JwtTokenProperties properties = jwtTokenProperties();
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(properties.getHeader(), properties.getSecret(), properties.getExpiration());
        return jwtTokenUtils;
    }
}
