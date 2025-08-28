package com.sist.ex0828;

import mybatis.dao.MemoDAO;
import mybatis.vo.MemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemoControl {
    // 원하는 DAO를 명시(자동화 되도록 지정)
    @Autowired
    private MemoDAO memoDAO;

    // 만약 session, request가 필요해지면 Autowired로 지정해 사용하면됌
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/memo/memolist")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        MemoVO[] ar = memoDAO.getTotal();

        // 받은 배열을 mv에 저장(뷰페이지 사용할 수 있다.)
        mv.addObject("ar", ar);

        // 뷰페이지 [경로]지정
//        mv.setViewName("memo/list");
        mv.setViewName("memo/memolist");
        // WEB-INF/jsp/memo/list.jsp를 의미함
        return mv;
    }

    @RequestMapping(value="/memo/add", method = RequestMethod.POST)
    public ModelAndView add(MemoVO vo) {

        vo.setIp(request.getRemoteAddr()); // ip 저장

        memoDAO.add(vo); // DAO 호출하면서 vo를 줌

        ModelAndView mv = new ModelAndView("redirect:/memo/memolist");

        return mv;
    }

}
