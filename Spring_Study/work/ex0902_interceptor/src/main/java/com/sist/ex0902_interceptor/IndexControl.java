package com.sist.ex0902_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControl {

    @RequestMapping("/") // ex) naver.com 과 같이 주소만 입력하고 엔터를 한 경우
    public String index() {
        return "index"; // jsp/index.jsp로 forward 됨
    }

    @RequestMapping("/sub/bravo")
    public String login() {
        return "sub_tab";
    }
}
