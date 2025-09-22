package com.sist.ex0918_token.domain.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0918_token.domain.member.entity.Member;
import com.sist.ex0918_token.domain.member.repository.MemberRepository;
import com.sist.ex0918_token.global.jwt.JwtProvider;
import com.sist.ex0918_token.global.result.ResultData;
import com.sist.ex0918_token.global.security.JwtUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Member join(String mId, String mName, String mPwd) {
        String pwd = passwordEncoder.encode(mPwd);

        Member mem = Member.builder()
            .mid(mId)
            .mname(mName)
            .mpwd(pwd)
            .build();

        Map<String, Object> map = new HashMap<>();
            map.put("mid", mem.getMid());
            map.put("mname", mem.getMname());
            map.put("mpwd", mem.getMpwd());

        String refreshToken = jwtProvider.getRefreshToken(map);
            mem.setRefreshToken(refreshToken);

        return this.memberRepository.save(mem);
    }

    public Member authAndMakeToken(String mid, String mpwd){
        Member member = null;
        String accessToken = null;
        try{
            member = memberRepository.findByMid(mid).orElseThrow(() ->
                                        new RuntimeException("존재하지 않음"));
            System.out.println("Service: "+mpwd +":"+ member.getMpwd());
            // 위는 mid 값만 가지고 검색한 Member이므로 다시 비밀번호가 맞는지?
            // 확인해야 한다.
            if(passwordEncoder.matches(mpwd, member.getMpwd())){
                // 사용자의 mid를 받아 객체를 생성하려고 하는데 만약 객체를 생성하지 못한다면 "존재하지 않음"을 보여줌
                // 여기는 위의 RuntimeException이 발생하지 않을 때만 수행함
                // 회원 정보를 가지고 토큰 생성
                Map<String, Object> map = new HashMap<>();
                map.put("idx", member.getB_idx());
                System.out.println("bidx");
                map.put("mid", member.getMid());
                System.out.println("mid");
                map.put("mname", member.getMname());
                System.out.println("name");
                map.put("write_date", member.getWrite_date().toString());
                System.out.println("여긴가?");

                // accessToken = jwtProvider.genToken(map, 60*60);
                accessToken = jwtProvider.getAccessToken(map);
                System.out.println("accessToken 생성됌");

                // String refreshToken = jwtProvider.genToken(map, 60*60*3); // 유효시간이 3시간인 토큰 발급
                String refreshToken = jwtProvider.getRefreshToken(map); // 유효시간이 100일인 토큰 발급
                System.out.println("리프레시토큰 생성됌");

                member.setAccessToken(accessToken);
                System.out.println("setAccess");
                member.setRefreshToken(refreshToken);
                System.out.println("serRefreshToken");

                // DB에 UPDATE할거면 여기서 해도 된다.
                memberRepository.updateRefreshToken(member.getB_idx(), refreshToken);
                System.out.println("디비저장");
            }// if(비밀번호 확인) end
        }catch(Exception e){  // 예외가 발생하면 member=null을 넣어 전달되지 않게 함
            System.out.println("MemberService의 Member Null");
            member = null;
        }
        System.out.println("ACCESSTOKEN : "+accessToken);

        return member;
    }

    public ResultData<String> refreshAccessToken(String refreshToString){
        Member member = null;

        member = memberRepository.findByRefreshToken(refreshToString).orElseThrow(() ->
            new RuntimeException("존재하지 않는 ID"));

        // 토큰 생성할 때 넣어줄 Payload값들 준비
        Map<String, Object> map = new HashMap<>();
        map.put("idx", member.getB_idx());
        map.put("mid", member.getMid());
        map.put("mname", member.getMname());
        map.put("write_date", member.getWrite_date());

        String accessToken = jwtProvider.getAccessToken(map);

        // 요청한 곳으로 보낼 JSON 자원 준비
        int cnt = 0;
        String msg = "fail";
        if(member != null){
            cnt = 1;
            msg = "success";
        }
        return ResultData.of(cnt, msg, accessToken);
    }

    public boolean validateToken(String token){
        return jwtProvider.verify(token);
    }

    public JwtUser getUserFromAccessToken(String accessToken){
        Map<String, Object> payload = jwtProvider.getClaims(accessToken);
        String mid = (String)payload.get("mid");
        String mname = (String)payload.get("mname");
        List<GrantedAuthority> authorities = new ArrayList<>();
    return new JwtUser(mid, mname, "", authorities);
}
}
