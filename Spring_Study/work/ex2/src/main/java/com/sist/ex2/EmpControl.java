package com.sist.ex2;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpControl {
    @Autowired
    private EmpDAO empDAO;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list");
        EmpVO[] ar = empDAO.getTotal();
        mv.addObject("ar", ar);
        return mv;
    }
}
