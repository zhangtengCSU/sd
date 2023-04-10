package com.reach.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    // Token key
    private static final String SECRET = "cFTkAoAXdPY!6#&@qHpxBXqs8g4mX&OB";

    /**
     * create jwt token
     */
    public static String createToken(String claim, String id) {
        // Create a Token generator
        JWTCreator.Builder builder = JWT.create();
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        // Fill header
        builder.withHeader(header);
        // Fill payload
        builder.withClaim(claim, id);
        // Expired in 60 minutes
        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000L * 2);
        builder.withExpiresAt(date);
        // Build a token
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * verify jwt token
     */
    public static Boolean verifyToken(String token, URI uri) {
        log.info(uri.getPath());
        if (uri.getPath().contains("/user/init/login") || uri.getPath().contains("/user/init/regis")) {
            log.info("Allow paths have no need to Verify");
            return true;
        }

        try {
            log.info("Verify Token");
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return verify != null;
        }
        // token expired & JWTDecodeException
        catch (TokenExpiredException e) {
            log.info("Verify Expired");
            throw ReachException.build(ResponseEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException ex) {
            return false;
        }
        // other exception
        catch (Exception ex) {
            // verify false, not do anything
        }
        return false;
    }

    public static Boolean verifyToken(String token) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return verify != null;
        }
        // token expired & JWTDecodeException
        catch (TokenExpiredException e) {
            throw ReachException.build(ResponseEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException ex) {
            return false;
        }
        // other exception
        catch (Exception ex) {
            // verify false, not do anything
        }
        return false;
    }

    /**
     * fetch the id in token
     */
    public static String fetchIdFromToken(String token, String claim) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim(claim).asString();
    }
}