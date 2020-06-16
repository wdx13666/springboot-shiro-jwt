package com.wdx.springbootshirojwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * 用来进行签名和效验Token
 *
 * @author wdx
 **/
public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @param secret   用户的密码
     * @return 正确: true；不正确：false
     */
    public static boolean verify(String token, String username, String secret) {
        // 根据密码生成JWT校验器
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 校验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取用户名
     *
     * @param token token中包含了用户名
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param username 用户名
     * @param secret   密码
     * @return 加密的TOKEN
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带用户信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }}
