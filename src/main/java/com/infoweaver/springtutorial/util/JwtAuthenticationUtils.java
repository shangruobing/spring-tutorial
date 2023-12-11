package com.infoweaver.springtutorial.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.security.sasl.AuthenticationException;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author Ruobing Shang 2023-09-28 16:52
 */
public class JwtAuthenticationUtils {
    /**
     * 7天的过期时间
     */
    private final static long TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L;
    private final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static Map<String, String> createToken(String userId, String phone) {
        String token = Jwts.builder()
                .setId(userId)
                .setSubject(phone)
                .signWith(SECRET_KEY)
                /**
                 * 登录时间 签发时间
                 */
                .setIssuedAt(new Date(System.currentTimeMillis()))
                /**
                 * 过期时间
                 */
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .compressWith(CompressionCodecs.GZIP)
                .claim("userId", userId)
                .claim("phone", phone)
                .compact();
        return Map.of("phone", phone, "access_token", token);
    }

    /**
     * 解析token，成功则返回用户的id
     *
     * @param token JWT
     * @return 用户的id
     * @throws AuthenticationException 认证失败
     */
    public static Claims parseAuth(String token) throws AuthenticationException {
        try {
            String[] charSequence = token.split(" ");
            String auth = charSequence[charSequence.length - 1];
            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(auth).getBody();
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new AuthenticationException("User Authentication failed. A token is required.");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("User Authentication failed. Expired token.");
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("User Authentication failed. Invalid token.");
        } catch (SignatureException e) {
            throw new AuthenticationException("User Authentication failed. Illegal token.");
        } catch (JwtException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    public static void main(String[] args) throws AuthenticationException {
        Map<String, String> auth = JwtAuthenticationUtils.createToken("123456", "若水");
        System.out.println(auth);
        System.out.println(JwtAuthenticationUtils.parseAuth(auth.get("access_token")));
    }
}
