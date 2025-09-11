package com.sist.ex0911_jwt.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtProvider {
    
    @Value("${custom.jwt.secretKey}") // spring의 Value를 import 해야한다.
    private String secretKeyCode;

    private SecretKey secretKey;

    public SecretKey getSecretKey(){
        if(secretKey == null){
            String encoding = Base64.getEncoder().encodeToString(secretKeyCode.getBytes());

            // scretkey는 숫자와 문자들을 마음대로 길게 기술한 것이다.(별 의미없음)
            // 그 값(secretKeyCode)을 가지고 jwt키를 만들어야 한다. 이때
            // jjwt라는 라이브러리를 사용하면 편하다
            secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
        }
        return secretKey;
    }

    public String genToken(Map<String, Object> map, int seconds){
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now+1000L*seconds);
        // 1000L*seconds : 밀리초로 변환

        JwtBuilder jwtBuilder = Jwts.builder().subject("SIST")
                                    .expiration(accessTokenExpiresIn);
                                    
        Set<String> keys = map.keySet(); // 반복자 처리하기 위해 Set 구조화
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            Object value = map.get(key);

            jwtBuilder.claim(key, value);
            /*
                JWT(JSON Web Token)은 크게 3가지 영역으로 나뉜다.
                    1. Header(알고리즘, 타입)
                    2. Payload(데이터)
                    3. Signature(서명)
                    이 세가지 중 Payload(데이터) 안에 들어있는 정보(data) 단위를 Claim 이라 한다.
             */
        } // while end
        String jwt = jwtBuilder.signWith(getSecretKey()).compact();

        return jwt;
    }

    // 토큰이 만료되었는지? 확인
    public boolean verify(String token){
        boolean value = false;

        try{
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (Exception e) {
            // 유효기간이 만료되면 예외발생됨
            value = true;
        }

        return value;
    }

    // 토큰에 담긴 사용자 정보(Claims)를 반환한다.
    public Map<String, Object> getClaims(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }
}
