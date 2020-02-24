package com.lin.util.config.properties;

import lombok.Data;

/**
 * JwtTokenProperties配置类
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
public class JwtTokenProperties {

    /**
     * 头部信息
     */
    private String header = "Authorization";

    /**
     * 密钥
     */
    private String secret = "imms";

    /**
     * 过期时间(默认7天)
     */
    private long expiration = 604800L;
}
