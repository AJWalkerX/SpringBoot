package com.ajwalker.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {
    /*
     * Token oluşturmak için gerekli olan parametreler;
     * SecretKey -> Token imzalalamak için gerekli şifre
     * Issuer -> jwt token sahibine ait bilgi
     * IssuerAt -> token üretilme zamanı
     * ExpiresAt -> token geçerlilik son zamanı, bitiş anı
     * Claim -> içersinde KEY-VALUE şeklinde değer saklayan nesneler.
     * NOT : Claim nesneleri içerisinde bulunan değerler açık olarak tutulur bu nedenle gizli kalması gereken
     * değerleri buraya eklemeyiniz.
     * Sign -> imzalama için kullanılır. Mutlaka bir şifreleme algoritması vermek gerekir.
     */
    /*Token oluşturmak için gerekli parametreler*/
    @Value("${java15.jwt.secret-key}")
    private String SecretKey;
    @Value("${java15.jwt.issuer}")
    private String Issuer;
    private final Long ExDate = 1000L * 40; //40sn sonra iptal olacak!

    public String createToken(Long authId) {
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + ExDate);
        Algorithm algorithm = Algorithm.HMAC512(SecretKey);
        String token = JWT.create()
                .withAudience()
                .withIssuer(Issuer)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .withClaim("authId", authId)
                .withClaim("key", "X13")
                .sign(algorithm);
        return token;
    }

    public Optional<Long> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SecretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (Objects.isNull(decodedJWT))//Eğer token doğrulanamaz ise null döner biz de empty olarak return ederiz.
                return Optional.empty();
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}
