package com.sist.ex0901_json;

import jsonEx.output.DataVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonControl {

    @RequestMapping("/test") // 호출할 때 /bbs/test로 호출해야함
    public String test() {
        return "ex1"; // view/ex1.jsp
    }

    @RequestMapping(value="callTest", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> json() {
        Map<String, Object> map = new HashMap<>();
//        map.put("ename", "마동석");
//        map.put("email", "ma@korea.com");
        DataVO[] ar = new DataVO[3];

        ar[0] = new DataVO("마동석", "ma@korea.com");
        ar[1] = new DataVO("이도", "ma@korea.com");
        ar[2] = new DataVO("을불", "ma@korea.com");
        map.put("ar", ar);
        map.put("length", ar.length);

        return map;
    }
}
