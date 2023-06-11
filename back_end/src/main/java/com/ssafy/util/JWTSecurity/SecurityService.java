package com.ssafy.util.JWTSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Service
public class SecurityService {
    // 원래 비밀키는 여기 있으면 안됌

    private static final String SECRET_KEY = "akdDJkUDAfifhFHFKAIFhmOFHAMFHAMdoFGMH#*@jdIFHDO";

    public String createToken(String subject, long expTime){
        //만료 시간이 0보다 작으면 안된다
        if(expTime <= 0){
            throw new RuntimeException("만료시간이 0보다 커야함 ");
        }
        //알고리즘 선택 단계
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 바이트 단위의 시크릿 키를 만들어야함
        // Stinrg 시크릿 키를 바이트로 만들어줌

        byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        // 키를 만들어줌 자바에서 제공하는 키를 만들어주는 모듈을 이용
        //바이트 형태의 키랑 알고리즘을 넣어서 만듬
        Key SingningKey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());


        //subject은 보통 유저 id값을 넣음 , 비밀번호는 시크릿 키를 만드는데 활용함
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, SingningKey)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

    }


    public String getSubject(String token){
        //Claims payLoad내 담기는 내용
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)) //복호화 시킴
                .parseClaimsJws(token) // 토큰을 넣어서 풀어줌
                .getBody();
        return claims.getSubject();


    }

}
