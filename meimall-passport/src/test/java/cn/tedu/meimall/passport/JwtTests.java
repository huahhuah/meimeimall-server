package cn.tedu.meimall.passport;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {
    String secretKey = "k4^&32flj5Ss(Jf&*(5%DK3da";

    @Test
    void generate() {
        Date date = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 998);
        claims.put("username", "helloworld");

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload：Claims
                .setClaims(claims)
                .setExpiration(date)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // compact
                .compact();
        System.out.println(jwt);
    }

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyQWdlbnQiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTE1LjAuMC4wIFNhZmFyaS81MzcuMzYiLCJpZCI6MSwiZXhwIjoxNjk0MTM1OTg3LCJ1c2VybmFtZSI6InJvb3QiLCJyZW1vdGVBZGRyIjoiMDowOjA6MDowOjA6MDoxIn0.gqgI3Ie_CPhbfyPA2R_sl-52_rVPBHutFFyfXVUcDLE";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        String remoteAddr = claims.get("remoteAddr", String.class);
        String userAgent = claims.get("userAgent", String.class);
        // String authorityListJsonString = claims.get("authorityListJsonString", String.class);
        // List<GrantedAuthority> authorities = claims.get("authorities", List.class);
        System.out.println("id = " + id);
        System.out.println("username = " + username);
        System.out.println("remoteAddr = " + remoteAddr);
        System.out.println("userAgent = " + userAgent);
        // System.out.println("authorityListJsonString = " + authorityListJsonString);
        // System.out.println("authorities = " + authorities);

        // List<SimpleGrantedAuthority> authorities = JSON.parseArray(authorityListJsonString, SimpleGrantedAuthority.class);
        // System.out.println("authorities = " + authorities);
        // System.out.println("authority class = " + authorities.get(0).getClass());
    }
}
