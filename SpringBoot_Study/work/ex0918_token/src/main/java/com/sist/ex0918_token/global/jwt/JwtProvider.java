package com.sist.ex0918_token.global.jwt;

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

    // 계속 바깥의 파일 키를 가져오면 부담이 되니까 멤버변수에 빼놓음
    private SecretKey secretKey;

    public SecretKey getSecretKey(){
        if(secretKey == null){

            // application-secure.yml에 있는 securetKey의 값을 암호화
            String encoding = Base64.getEncoder().encodeToString(secretKeyCode.getBytes());
            // Base64 : 암호화

            // scretkey는 숫자와 문자들을 마음대로 길게 기술한 것이다.(별 의미없음)
            // 그 값(secretKeyCode)을 가지고 jwt키를 만들어야 한다. 이때
            // jjwt라는 라이브러리를 사용하면 편하다
            secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
        }
        return secretKey;
    }

    public String genToken(Map<String, Object> map, int seconds){
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now+1000L*seconds); // 발급한 시간으로부터 {seconds}초 뒤에 만료됌
        // 컴퓨터는 밀리초 단위로 움직이기 때문에
        // 1000L*seconds : 밀리초로 변환 - 만료시간

        JwtBuilder jwtBuilder = Jwts.builder().subject("SIST")
                                    .expiration(accessTokenExpiresIn);
                                    //
        // Map구조는 index가 없어서 반복문을 수행하게 하기 위해서는 index를 만들어줘야한다
        // Iterator는 Set구조의 있는 나열화 시키는 객체인데 Map을 Set구조로 만들고,
        // Set에 Iterator를 지정하여 반복문처리 -> key와 value를 얻어낸다.
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
        // 서명한다
        String jwt = jwtBuilder.signWith(getSecretKey()).compact();

        return jwt;
    }

    // 토큰이 만료되었는지? 확인
    public boolean verify(String accessToken, String refreshToken){
        boolean value = true;

        try{
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(accessToken);
        } catch (Exception e) {
            // refresh 검사
            try{
                Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(refreshToken);
                // 토큰에 들어있는 mid만 있으면 authAndMakeToken를 호출해서 
                // 새로운 토큰 발급 가능
                
            } catch (Exception a) {
                // 유효기간이 만료되면 예외발생됨
                value = false;
            }
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
