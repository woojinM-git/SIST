package com.sist.ex1;

import ex1.vo.DataVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAction3 {

    @RequestMapping("/ex6")
    public ModelAndView ex() {
        ModelAndView mv = new ModelAndView("ex6");

        // DataVO를 3개 생성
        DataVO[] ar = new DataVO[3];
        ar[0] = new DataVO();
        ar[0].setName("일지매");
        ar[0].setPhone("010-1212-2323");

        ar[1] = new DataVO();
        ar[1].setName("마루치");
        ar[1].setPhone("010-2323-3434");
        
        ar[2] = new DataVO();
        ar[2].setName("이도");
        ar[2].setPhone("010-3434-4545");

        // 배열을 뷰페이지에서 표현하기 위해 mv에 저장!
        mv.addObject("ar", ar);

        return mv;
    }
}
