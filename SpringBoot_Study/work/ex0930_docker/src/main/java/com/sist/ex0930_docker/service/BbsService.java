package com.sist.ex0930_docker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_docker.mapper.BbsMapper;
import com.sist.ex0930_docker.vo.BbsVO;

@Service
public class BbsService {
    @Autowired
    private BbsMapper bbsMapper;

    public int getTotalCount(String bname, String searchType, String searchValue){
        Map<String, Object> map = new HashMap<>();
        if(bname != null)
            map.put("bname", bname);
        if(searchType != null)
            map.put("searchType", searchType);
        if(searchValue != null)
            map.put("searchValue", searchValue);
        return bbsMapper.totalCount(map);
    }

    public List<BbsVO> getList(@Param("bname")String bname, String searchType, String searchValue, int begin, int end){
        return bbsMapper.list(bname, searchType, searchValue, begin, end);
    }

    public BbsVO getBbs(String b_idx){
        return bbsMapper.get_bbs(b_idx);
    }

    public int addBbs(BbsVO vo){
        return bbsMapper.add(vo);
    }

    public int editBbs(String subject, String content, String fname, String oname, int b_idx){
        Map<String, Object> map = new HashMap<>();
        if(subject != null)
            map.put("subject", subject);
        if(content != null)
            map.put("content", content);
        if(fname != null)
            map.put("fname", fname);
        if(oname != null)
            map.put("oname", oname);
        map.put("b_idx", b_idx); // 이건 꼭 넘어오며 꼭 찾아서 수정하도록 함
        return bbsMapper.edit(map);
    }

    public int delBbs(String b_idx){
        return bbsMapper.del(b_idx);
    }
}
