package com.sist.ex0905_dept.controller;

import com.sist.ex0905_dept.service.DeptService;
import com.sist.ex0905_dept.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DeptControl {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept")
    public ModelAndView total(){
        ModelAndView mv = new ModelAndView();
        DeptVO[] ar = deptService.total();
        mv.addObject("ar", ar);
        mv.setViewName("dept");
        return mv;
    }
}
