package com.sist.ex0905_bbs.service;

import com.sist.ex0905_bbs.mapper.BbsMapper;
import com.sist.ex0905_bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsService {
    @Autowired
    private BbsMapper bbsMapper;

    public BbsVO[] total(){
        BbsVO[] ar = null;
        List<BbsVO> list = bbsMapper.total();
        if(list.size() > 0){
            ar = new BbsVO[list.size()];
            ar = list.toArray(ar);
        }
        return ar;
    }

    public BbsVO[] search(String searchType, String searchValue){
        BbsVO[] ar = null;
        List<BbsVO> list = bbsMapper.search(searchType, searchValue);
        if(list.size() > 0){
            ar = new BbsVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
