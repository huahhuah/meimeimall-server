package cn.tedu.meimall.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具類
 */
public class JwtUtils {

    /**
     * 生成JWT
     * @param claims     存入到JWT中的資料
     * @param expiration JWT過期時間
     * @param secretKey  密鑰
     * @return  JWT資料
     */
    public static synchronized String generate(Map<String , Object> claims, Date expiration, String secretKey){
        return Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 解析JWT
     * @param jwt       JWT資料
     * @param secretKey 生成JWT時使用的密鑰
     * @return  解析結果
     */
    public static synchronized Claims parse(String jwt, String secretKey){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}
