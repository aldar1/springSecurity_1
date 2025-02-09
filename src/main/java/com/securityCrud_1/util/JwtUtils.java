package com.securityCrud_1.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = authentication.getPrincipal().toString();

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException jwtEx) {
            throw new JWTVerificationException("Token invalid, not Authorize");
        }
    }

    public String extracUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().toString();
    }

    public Claim geSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String,Claim> returnAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }

}
