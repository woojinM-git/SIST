package com.sist.ex0828_bbs;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BbsControl {

    @Autowired
    private BbsDAO bbsDAO;

    @Autowired
    private HttpSession session;

    int numPerPage = 7; // 한 페이지에 보여질 게시물 수
    int pagePerBlock = 5; // 한 블럭 당 보여질 페이지 수

    @RequestMapping("/bbs/list")
    public ModelAndView list(String bname, String cPage) {
        // 페이징 처리 -----------------------------------------------------
        if(bname == null || bname.equals(""))
            bname = "BBS";

        Paging page = new Paging(numPerPage, pagePerBlock);

        // 총 페이지 수를 구하기 위해 총 게시물 수를 알아내야 함!
        int totalCount = bbsDAO.getTotalCount(bname);
        page.setTotalCount(totalCount);
        if(cPage == null || cPage.equals(""))
            cPage = "1";
        page.setNowPage(Integer.parseInt(cPage));
        // ----------------------------------------------------------------
        ModelAndView mv = new ModelAndView();
        // 뷰 페이지에서 표현할 목록(배열)을 받자!
        BbsVO[] ar = bbsDAO.getList(bname, page.getBegin(), page.getEnd());
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("nowPage", page.getNowPage());
        mv.setViewName("bbs/list");
        return mv;
    }

    // 삭제
    @RequestMapping("/bbs/del")
    public ModelAndView view(String b_idx) {
        ModelAndView mv = new ModelAndView();
        int i = bbsDAO.delBbs(b_idx);
        // 글이 삭제되면 목록으로 가야함
        mv.setViewName("redirect:/bbs/list");
        return mv;
    }

//    // 수정
//    @RequestMapping("/bbs/correct")
//    public ModelAndView view(String subject, String content, String b_idx) {
//        ModelAndView mv = new ModelAndView();
//        int i = bbsDAO.correct(subject, content, b_idx);
//        // 수정이 완료되면 다시 해당 게시글을 보여줘야함
//        mv.setViewName("redirect:/bbs/view?b_idx=" + b_idx);
//        return mv;
//    }

    // 보기
    @RequestMapping("/bbs/view")
    public ModelAndView view(String bname, String b_idx, String cPage) {
        ModelAndView mv = new ModelAndView();

        List<BbsVO> list = null;
        // 세션으로부터 이름이 r_list라는 이름으로 저장된 객체를 얻어낸다.
        Object obj = session.getAttribute("r_list");
        if(obj != null)
            list = (List<BbsVO>) obj;
        else {
            // 처음으로 게시판 들어온 경우
            list = new ArrayList<>();
            session.setAttribute("r_list", list);
        }

        // 이제 list에서 인자로 받은 b_idx값과 같은 값을 가진 BbsVO를
        // list에서 검색한다.
        boolean check = false; // 이 false가 계속 유지된다면 찾지 못한 경우
        for(BbsVO bvo : list) {
            if(bvo.getB_idx().equals(b_idx)) {
                check = true;
                break;
            }
        } // for end
        // check 변수의 값이 false면 조회수 증가
        if(!check) {
            bbsDAO.hit(b_idx); // 조회수 증가
        }

        BbsVO vo = bbsDAO.getBbs(b_idx);
        if(vo != null) {
            if(!check)
                list.add(vo); // 다음에 같은 게시물을 클릭하면 조회수 증가를 하지 않는다.
            // 검색된 vo를 뷰페이지에서 봐야 하므로 mv에 저장
            mv.addObject("vo", vo);
        }
        mv.setViewName("bbs/view");
        return mv;
    }
    
    // 글쓰기
    @RequestMapping("/bbs/write")
    public ModelAndView write(String bname) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("bname", bname);
        mv.setViewName("bbs/write");
        return mv;
    }

    // 글저장
    @RequestMapping("/bbs/add")
    public ModelAndView write(BbsVO vo) {
        ModelAndView mv = new ModelAndView();
        int i = bbsDAO.add(vo);
        // 글이 저장되면 목록을 보여줘야함
        return mv;
    }
}
