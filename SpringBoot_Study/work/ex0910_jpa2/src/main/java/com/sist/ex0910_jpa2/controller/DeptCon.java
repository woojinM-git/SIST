package com.sist.ex0910_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa2.service.DeptService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/dept")
public class DeptCon {
    
    @Autowired
    private DeptService deptService;

    @RequestMapping("/all")
    public Object all() {
        return deptService.findAll();
    }
    
    @RequestMapping("/find")
    public Object find(@RequestParam("dept") Long deptno) {
        return deptService.findByDeptno(deptno);
    }
    

}
