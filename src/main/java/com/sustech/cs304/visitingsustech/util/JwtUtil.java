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

    //    private byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
    private byte[] keyBytes = {-75, 16, -112, -27, 81, -57, -109, 7, -8, 117, -106, 57, 103, 75, 102, 56, 80, 93, 40, 86, 38, 86, 36, 63, 114, -75, -95, -79, -19, -17, 120, -42, -89, 70, 75, -22, -15, -77, -23, 119, 77, 29, 112, -115, 14, 8, 17, -43, 52, 91, -100, 82, 48, -32, -49, -51, -28, -3, 93, -14, 121, -19, 104, 56};

    private SecretKey secret = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    private long expire = 604800;
    private String header = "Authorization";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getToken(String session) {
        System.out.println(secret);
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