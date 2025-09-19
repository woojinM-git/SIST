package com.sist.ex0918_token.domain.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0918_token.domain.member.entity.Member;
import com.sist.ex0918_token.domain.member.repository.MemberRepository;
import com.sist.ex0918_token.global.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Member join(String mid, String mname, String mpwd){
        Member mem = Member.builder()
            .mid(mid)
            .mname(mname)
            .mpwd(mpwd)
            .build();
        return memberRepository.save(mem);
    }
    
    public Member authAndMakeToken(String mid, String mpwd){
        System.out.println("MemService");
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
                map.put("mid", member.getMid());
                map.put("mname", member.getMname());
                map.put("write_date", member.getWrite_date().toString());

                accessToken = jwtProvider.genToken(map, 60*60);

                String refreshToken = jwtProvider.genToken(map, 60*60*3); // 유효시간이 3시간인 토큰 발급

                member.setAccessToken(accessToken);
                member.setRefreshToken(refreshToken);

                // DB에 UPDATE할거면 여기서 해도 된다.
                memberRepository.save(member);
            }// if(비밀번호 확인) end
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("ACCESSTOKEN : "+accessToken);

        return member;
    }
}
