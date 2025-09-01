package com.sist.ex0901_json;

import jsonEx.input.EmpDAO;
import jsonEx.output.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmpControl {

    @Autowired
    private EmpDAO empDAO;

    @RequestMapping(value="/empList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        EmpVO[] ar = empDAO.getList();

        map.put("ar", ar);
        map.put("length", ar.length);

        return map;
    }
}
