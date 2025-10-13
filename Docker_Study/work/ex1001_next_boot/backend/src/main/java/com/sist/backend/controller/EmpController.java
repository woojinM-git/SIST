package com.sist.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.backend.service.EmpService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/emp")
public class EmpController {
    
    @Autowired
    private EmpService empService;

    @RequestMapping("/all")
    public Map<String, Object> all() {
        Map<String, Object> map = new HashMap<>();
        map.put("ar", empService.getAll());
        return map;
    }
    
}
