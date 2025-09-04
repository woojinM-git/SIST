package com.sist.ex0904;

import data.vo.DataVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController // ResponseBody와 Control을 합친게 RestController
public class TextControl {

    @GetMapping("/index")
    public ModelAndView index(String code) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.setViewName("index");
        return mav;
    }

    /*
        아래에 있는 코드는 RestController에서 사용 할 때에 return "test"를 했을 때 문자열만 보내서 해당 문자열만 홈페이지에 보여준다.
     */
//    @GetMapping("/") // http://localhost:8080
//    public String main(Model model, String code) {
//        if(code != null) {
//            DataVO vo = new DataVO();
//            vo.setStr(code);
//            model.addAttribute("vo", vo);
//        }
//        return "test";
//    }

    @GetMapping("/") // http://localhost:8080
    public ModelAndView main(String code) {
        ModelAndView mv = new ModelAndView();
        if(code != null) {
            DataVO vo = new DataVO();
            vo.setStr(code);
            mv.addObject("vo", vo);
        }
        mv.setViewName("test");
        return mv;
    }
}
