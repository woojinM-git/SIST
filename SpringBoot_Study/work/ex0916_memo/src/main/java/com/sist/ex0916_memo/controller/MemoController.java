package com.sist.ex0916_memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0916_memo.service.MemoService;
import com.sist.ex0916_memo.vo.MemoVO;


@RestController
@RequestMapping("/memo")
public class MemoController {
    @Autowired
    private MemoService memoService;

    @RequestMapping("/all")
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        List<MemoVO> list = memoService.all();

        // 준비된 map에 list를 저장하자
        map.put("m_list", list);
        map.put("lenght", list.size());

        return map;
    }

    @RequestMapping("/one")
    public MemoVO getVO(String idx) {
        return memoService.one(idx);
    }
}
