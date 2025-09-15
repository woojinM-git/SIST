package com.sist.ex0911_jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0911_jwt.service.MemberService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemberControl {
    @Autowired
    private MemberService memberService;

    @GetMapping("/code")
    public Map<String, Object> code(@RequestParam("mid") String mid) {
        Map<String, Object> result = new HashMap<>();
        String token = memberService.makeToken(mid);
        result.put("access_token", token);

        return result;
    }
    
}
