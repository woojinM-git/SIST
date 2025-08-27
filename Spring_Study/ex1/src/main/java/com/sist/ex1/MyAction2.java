package com.sist.ex1;

import ex1.vo.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAction2 {
    private TestVO ttt;

    public TestVO getT3() {
        return ttt;
    }

    public void setT3(TestVO ttt) {
        this.ttt = ttt;
    }

    @RequestMapping("/ttt")
    public ModelAndView ex4() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ex5");

        // t3의 이름을 jsp에서 표현하기 위해 mv에 저장
        mv.addObject("ttt", ttt);
        return mv;
    }
}
