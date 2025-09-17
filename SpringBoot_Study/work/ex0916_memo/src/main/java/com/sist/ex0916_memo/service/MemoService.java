package com.sist.ex0916_memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0916_memo.mapper.MemoMapper;
import com.sist.ex0916_memo.vo.MemoVO;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public List<MemoVO> all(){
        List<MemoVO> list = memoMapper.all();
        return list;
    }

    public MemoVO one(String idx){
        MemoVO vo = memoMapper.one(idx);
        return vo;
    }
}
