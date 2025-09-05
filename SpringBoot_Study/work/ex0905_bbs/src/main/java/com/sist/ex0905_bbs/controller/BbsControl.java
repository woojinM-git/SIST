package com.sist.ex0905_bbs.controller;

import com.sist.ex0905_bbs.service.BbsService;
import com.sist.ex0905_bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BbsControl {
    @Autowired
    private BbsService bbsService;

    @GetMapping("/bbs")
    public ModelAndView total(){
        ModelAndView mav = new ModelAndView();
        ar = bbsService.total();
        mav.addObject("ar", ar);
        mav.setViewName("bbs");
        return mav;
    }

    BbsVO[] ar = null;

    @PostMapping("/search")
    public ModelAndView search(String searchType, String searchValue){
        ModelAndView mav = new ModelAndView();
        ar = bbsService.search(searchType, searchValue);
        mav.addObject("ar", ar);
        mav.setViewName("bbs");
        return mav;
    }

}
