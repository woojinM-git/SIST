package com.sist.ex0911_secure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0911_secure.mapper.MemberMapper;
import com.sist.ex0911_secure.vo.MemVO;

@Service
public class MemberService {
    
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    public int reg(MemVO vo){
        // reg.jsp에서 전달되는 m_id, m_pw, m_name등이 컨트롤러에서
        // vo로 받은 것을 그대로 현재 메서드의 vo라는 인자로 돌아온다.
        // 이중 비밀번호를 암호화 하자!
        String pw = passwordEncoder.encode(vo.getM_pw());
        vo.setM_pw(pw);


        return memberMapper.reg(vo);
    }

    // 로그인
    public MemVO login(MemVO vo){
        // DB로부터 vo에 있는 m_id를 보내
        // 해당 MemVO를 받아온다.
        MemVO mvo = memberMapper.login(vo.getM_id());

        if(mvo != null){
            // 이때 비밀번호가 일치하는지는
            // passwordEncoder에게 물어봐야 한다.
            if(passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw())){
                // true = 사용자가 입력한 비밀번호와 DB의 암호화 된 비밀번호가 일치하는 경우
                return mvo;
            } // false = 사용자가 입력한 비밀번호와 DB의 암호화 된 비밀번호가 일치하지 않은 경우
                // null을 반환함
        }
        // 만약 mvo가 null이라면 아이디가 잘못된 경우임
        return null;
      
    }
}
