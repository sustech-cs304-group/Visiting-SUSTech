package com.sustech.cs304.visitingsustech.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Data
@Component
public class JwtUtil {

    private byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
    private SecretKey secret = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    private long expire = 604800;
    private String header = "Authorization";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getToken(String session) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(session)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getOpenidFromToken(String token) {
        String openid;
        String session;
        try {
            Claims claims = getClaimByToken(token);
            session = claims.getSubject();
            EncryptUtil encryptUtil = new EncryptUtil();
            openid = objectMapper.readTree(encryptUtil.decrypt(session)).get("openid").asText();
            return openid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public void verifyToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}