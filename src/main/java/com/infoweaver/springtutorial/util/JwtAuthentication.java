package com.infoweaver.springtutorial.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.security.sasl.AuthenticationException;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-04 20:12
 */
public class JwtAuthentication {
    private final static long TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L;
    private final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public static Map<String, String> createToken(String userId, String username) {
        String token = Jwts.builder()
                .setId(userId)
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .compressWith(CompressionCodecs.GZIP)
                .claim("userId", userId)
                .claim("username", username)
                .compact();
        return Map.of("username", username, "auth", token);
    }

    public static String parseAuth(String token) throws AuthenticationException {
        try {
            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getId();
        } catch (IllegalArgumentException e) {
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
        Map<String, String> auth = JwtAuthentication.createToken("33", "33");
        System.out.println(auth);
        System.out.println(JwtAuthentication.parseAuth(auth.get("auth")));
    }
}
