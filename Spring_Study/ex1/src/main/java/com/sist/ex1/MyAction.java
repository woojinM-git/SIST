package com.sist.ex1;

import ex1.vo.Test3VO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAction {
    private Test3VO t3;

    public Test3VO getT3() {
        return t3;
    }

    public void setT3(Test3VO t3) {
        this.t3 = t3;
    }

    @RequestMapping("/test")
    public ModelAndView ex4() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ex4");
        
        // t3의 이름을 jsp에서 표현하기 위해 mv에 저장
        mv.addObject("tt", t3);
        return mv;
    }
}
