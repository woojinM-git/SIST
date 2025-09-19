package com.sist.ex0918_token.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sist.ex0918_token.domain.bbs.entity.sevice.BbsService;
import com.sist.ex0918_token.domain.member.entity.Member;
import com.sist.ex0918_token.domain.member.service.MemberService;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    // 가짜 데이터(개발때만 미리 데이터들 넣어주기 위함)

    @Bean
    CommandLineRunner initData(BbsService bbsService, MemberService memberService, PasswordEncoder passwordEncoder){
        String pwd = passwordEncoder.encode("1111");

        return args -> {
            memberService.join("dojin", "도진", pwd);
            memberService.join("admin", "관리자", pwd);

            bbsService.save("제목1", "마루치", "테스트입니다.");
            bbsService.save("제목2", "아라치", "테스트입니다.");
            bbsService.save("제목3", "손오공", "테스트입니다.");
            bbsService.save("제목4", "도라에몽", "테스트입니다.");
        };
    }
}
