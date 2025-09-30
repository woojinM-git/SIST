package com.sist.ex0930_emp_self.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0930_emp_self.service.EmpService;
import com.sist.ex0930_emp_self.vo.EmpVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class EmpControl {

    @Autowired
    private EmpService empService;

    @RequestMapping("/emp")
    public Map<String, Object> requestMethodName() {
        Map<String, Object> map = new HashMap<>();

        List<EmpVO> list = empService.getlist();

        map.put("list", list);

        return map;
    }
}
