package com.sist.ex1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test2Action {

    @RequestMapping("/ex2")
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "반갑습니다.SIST"); // request.setAttribute 와 같은 움직임
        mv.setViewName("ex2"); // 경우에 따라 보여줘야하는 화면이 달라야 할 수 있기에 set으로 지정

        return mv;
    }

    @RequestMapping("/ex3")
    public ModelAndView execute2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("str", "Hello Spring"); // request.setAttribute 와 같은 움직임
        mv.setViewName("ex3"); // 경우에 따라 보여줘야하는 화면이 달라야 할 수 있기에 set으로 지정

        return mv;
    }
}
