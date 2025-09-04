package com.sist.ex0904_emp_service;

import mybatis.service.EmpMapper;
import mybatis.service.EmpService;
import mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpControl {

    @Autowired
    private EmpMapper empService;

    @GetMapping("/")
    public String list(Model model){
        EmpVO[] ar = empService.all();
        model.addAttribute("ar", ar);

        return "index";
    }

    @PostMapping("/search")
    public ModelAndView search(String type, String value){
        ModelAndView mv = new ModelAndView();
        EmpVO[] ar = empService.search(type, value);

        mv.addObject("ar", ar);
        mv.setViewName("search");
        return mv;
    }
}
