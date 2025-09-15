package com.sist.ex0911_jwt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0911_jwt.jwt.JwtProvider;
import com.sist.ex0911_jwt.repository.MemberRepo;
import com.sist.ex0911_jwt.vo.MemVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 멤버변수를 다 받는 생성자를 만들어줌
public class MemberService {
    private final MemberRepo memberRepo;
    private final JwtProvider jwtProvider;

    public String makeToken(String mid){
        MemVO mvo = null;
        String token = null;
        Optional<MemVO> vo = memberRepo.findBymId(mid);
        // if(vo.isPresent()){
        if(!vo.isEmpty()){
        mvo = vo.get(); // MemVO를 mvo에 넣어줌

        // mvo를 Map구조로 변환
        Map<String, Object> map = new HashMap<>();
        map.put("mIdx", mvo.getMIdx());
        map.put("mId", mvo.getMId());
        map.put("mName", mvo.getMName());

        token = jwtProvider.genToken(map, 60);
        } // if end
        return token;
    }

}
