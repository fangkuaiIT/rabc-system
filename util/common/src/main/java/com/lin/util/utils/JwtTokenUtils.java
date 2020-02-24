package com.lin.util.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * JwtTokenUtils
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
public class JwtTokenUtils implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8148003672297962680L;

    /**
     * CLAIM_KEY_USERNAME
     */
    private static final String CLAIM_KEY_USERNAME = "sub";
    /**
     * CLAIM_KEY_CREATED
     */
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 头部header
     */
    private String header;
    /**
     * 密钥
     */
    private String secret;

    /**
     * 过期时间
     */
    private long expiration;


    /**
     * Jwt token utils.
     *
     * @param header     the header
     * @param secret     the secret
     * @param expiration the expiration
     */
    public JwtTokenUtils(String header, String secret, long expiration) {
        this.header = header;
        this.secret = secret;
        this.expiration = expiration;
    }

    /**
     * 获取令牌用户名
     *
     * @param token the token
     * @return the string
     * @author : fangkuaiIt / 2019-01-09
     */
    public String getUserName(String token){
        return getClaims(token).getSubject();
    }

    /**
     * 获取令牌过期时间
     *
     * @param token the token
     * @return the date
     * @author : fangkuaiIt / 2019-01-09
     */
    public Date getExpirationDate(String token){
        return getClaims(token).getExpiration();
    }

    /**
     * 获取令牌创建时间
     *
     * @param token the token
     * @return the date
     * @author : fangkuaiIt / 2019-01-09
     */
    public Date getIssurdAtDate(String token){
        return getClaims(token).getIssuedAt();
    }

    /**
     * 生成令牌
     *
     * @param userName the user name
     * @return the string
     * @author : fangkuaiIt / 2019-01-09
     */
    public String generateToken(String userName){
        return doGenerateToken(null, userName);
    }

    /**
     * 生成令牌
     *
     * @param claims   the claims
     * @param userName the user name
     * @return the string
     * @author : fangkuaiIt / 2019-01-09
     */
    public String generateToken(Map<String, Object> claims, String userName){
        return doGenerateToken(claims, userName);
    }


    /**
     * 刷新令牌
     *
     * @param token the token
     * @return the string
     * @author : fangkuaiIt / 2019-01-09
     */
    public String refreshToken(String token){
        String userName = getUserName(token);
        return generateToken(userName);
    }


    /**
     * 验证令牌
     *
     * @param token             the token
     * @param userName          the user name
     * @param lastPasswordReset the last password reset
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-09
     */
    public Boolean validateToken(String token, @NonNull String userName, @NonNull Date lastPasswordReset){
        String tokenUserName = getUserName(token);
        Date tokenCreatedDate = getIssurdAtDate(token);

        return (userName.equals(tokenUserName)
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(tokenCreatedDate, lastPasswordReset)
        );
    }


    /**
     * 生成令牌
     *
     * @param claims  the claims
     * @param subject the subject
     * @return the string
     * @author : fangkuaiIt / 2019-01-09
     */
    private String doGenerateToken(Map<String, Object> claims, String subject){
        Date currentDate = new Date();
        Date expirationDate = calculateExpirationDate(currentDate);

        if (claims != null && claims.size() > 0){
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(currentDate)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, secretWithEncodeBase64())
                    .compact();
        }
        else {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(currentDate)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, secretWithEncodeBase64())
                    .compact();
        }
    }

    /**
     * 从令牌中获取数据申明
     *
     * @param token the token
     * @return the claims
     * @author : fangkuaiIt / 2019-01-09
     */
    private Claims getClaims(String token){
        return Jwts.parser()
            .setSigningKey(secretWithEncodeBase64())
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 判断token是否过期
     *
     * @param token the token
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-09
     */
    private Boolean isTokenExpired(String token){
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * token创建时间是否在最后一次修改密码之前
     *
     * @param created           the created
     * @param lastPasswordReset the last password reset
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-09
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){
        return (lastPasswordReset != null) && created.before(lastPasswordReset);
    }

    /**
     * 是否允许刷新令牌
     *
     * @param token             the token
     * @param lastPasswordReset the last password reset
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-09
     */
    private Boolean canTokenBeRefreshed(String token, Date lastPasswordReset){
        Date createdDate = getIssurdAtDate(token);
        return !isCreatedBeforeLastPasswordReset(createdDate, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 计算过期时间
     *
     * @param createdDate the created date
     * @return the date
     * @author : fangkuaiIt / 2019-01-09
     */
    private Date calculateExpirationDate(Date createdDate){
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    /**
     * 密钥Base64编码
     *
     * @return the byte [ ]
     * @author : fangkuaiIt / 2019-01-09
     */
    private byte[] secretWithEncodeBase64(){
        return Base64.getEncoder().encode(secret.getBytes());
    }
}
