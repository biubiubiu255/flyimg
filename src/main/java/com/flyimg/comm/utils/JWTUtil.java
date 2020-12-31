package com.flyimg.comm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * jwt工具类
 * @author yuanneng
 */
public class JWTUtil {

    /** 过期时间30天*24个小时*60分钟*60秒 **/
    private static final Long EXPIRE_TIME = 30*24*60*60*1000L;

    private static final String DEFAULT_NAME = "username";

    /** 默认加解密盐值 **/
    public static final String DEFAULT_SECRET = "mktdesk20200320";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String value) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(DEFAULT_NAME, value)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 解析token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String parseValue(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            long time = jwt.getExpiresAt().getTime();
            return jwt.getClaim(DEFAULT_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String parseValueAndVerify(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            long time = jwt.getExpiresAt().getTime();
            String content = jwt.getClaim(DEFAULT_NAME).asString();
            return verify(token, content) ? content : null;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim(DEFAULT_NAME, username)
                    .withExpiresAt(date)
                    .sign(algorithm);

    }

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username, Long expires) {
        Date date = new Date(System.currentTimeMillis() + expires);
        Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(DEFAULT_NAME, username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
