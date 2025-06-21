package cn.woyioii.justtakeaway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JWT 工具类，提供生成、解析和验证 Token 的功能。
 */
@Component
public class JwtUtil {
    private final String secretKey;

    @Value("${jwt.expiration:864000000}")
    private long expiration;

    /**
     * 构造函数，注入密钥
     *
     * @param secretKey 密钥
     */
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取签名密钥
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    /**
     * 生成 JWT Token
     *
     * @param username 用户名
     * @return JWT Token 字符串
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 解析 Token 获取用户名
     *
     * @param token Token 字符串
     * @return 用户名
     */
    public String parseUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 验证 Token 是否有效
     *
     * @param token Token 字符串
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 获取 Token 的过期时间
     *
     * @param token Token 字符串
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    /**
     * 判断 Token 是否已过期
     *
     * @param token Token 字符串
     * @return 是否过期
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
