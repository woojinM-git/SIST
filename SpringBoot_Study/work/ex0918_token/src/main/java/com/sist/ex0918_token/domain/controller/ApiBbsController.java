package com.sist.ex0918_token.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918_token.domain.bbs.entity.Bbs;
import com.sist.ex0918_token.domain.bbs.entity.sevice.BbsService;
import com.sist.ex0918_token.global.result.ResultData;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bbs")
public class ApiBbsController {
    private final BbsService bbsService;

    @GetMapping("")
    public ResultData<List<Bbs>> getList() {
        List<Bbs> list = bbsService.getList();
        String msg = "fail";
        if(list != null && list.size() > 0)
            msg = "success";
        return ResultData.of(list.size(), msg, list);
    }

    @GetMapping("/{b_idx}")
    public ResultData<Bbs> getBbs(@PathVariable("b_idx") Long b_idx) {
        Bbs bbs = bbsService.getBbs(b_idx);
        String msg = "fail";
        if(bbs != null)
            msg = "success";
        return ResultData.of(msg, bbs);
    }

    @PostMapping("/write")
    public ResultData<Bbs> write(@RequestBody Bbs bbs) {
        Bbs bvo = bbsService.save(bbs.getTitle(), bbs.getWriter(), bbs.getContent());
        String msg = "fail";
        if(bvo != null)
            msg = "success";

        // 저장된 결과가 bvo이고 그 안에 b_idx라는 기본키값도 가지고 있다.
        // b_idx값을 다른 테이블에 저장도 가능하다.
        return ResultData.of(msg, bvo);
    }
    
}
