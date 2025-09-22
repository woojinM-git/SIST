package com.sist.ex0918_token.domain.bbs.entity.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.sist.ex0918_token.domain.bbs.entity.Bbs;
import com.sist.ex0918_token.domain.bbs.entity.repository.BbsRepository;
import com.sist.ex0918_token.global.jwt.JwtProvider;
import com.sist.ex0918_token.global.security.JwtUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 초기화 되지 않은 final필드나, @NonNull이 붙은 필드들에 대해 값을 채워준다.
public class BbsService {
    private final BbsRepository bbsRepository;

    private final JwtProvider jwtProvider;

    public List<Bbs> getList(){
        return bbsRepository.findAll();
    }

    public Bbs getBbs(Long b_idx){
        Bbs bbs = null;
        Optional<Bbs> opt = bbsRepository.findById(b_idx);
        if(!opt.isEmpty())
            bbs = opt.get();
        return bbs;
    }

    public Bbs save(String title, String writer, String content){
        Bbs bbs = Bbs.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .build();
        return this.bbsRepository.save(bbs);
    }

    public JwtUser getUserFromAccessToken(String accessToken){
        // 인자로 받은 jwt accessToken으로 부터 payload만 받는다
        Map<String, Object> payload = jwtProvider.getClaims(accessToken);

        
        String mid = (String)payload.get("mid");
        String mname = (String)payload.get("mname");
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new JwtUser(mid, mname, "", authorities);
    }
}
