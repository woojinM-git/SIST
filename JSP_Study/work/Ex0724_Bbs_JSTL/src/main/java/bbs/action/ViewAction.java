package bbs.action;

import bbs.dao.BbsDAO;
import mybatis.vo.BbsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAction implements Action{

    public boolean checkBbs(List<BbsVO> list, BbsVO vo){
        boolean value = true;
        // list에 vo가 있는지? 판단하는 반복문
        for(BbsVO bvo : list){
            if(bvo.getB_idx().equals(vo.getB_idx())){
                // 리스트에 있는 경우
                value = false;
                break; // 반복문 탈출
            }
        } // for의 끝


        return value;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 파라미터 값 받기
        String b_idx = request.getParameter("b_idx");//기본키
        //String cPage = request.getParameter("cPage");// 목록보기에 사용할 페이지 값

        // 한번이라도 읽은 게시물들은 list에 담아서 HttpSession에 저장해 둔다.
        // 그럼 우선 HttpSession으로 부터 list를 얻어내자
        HttpSession session = request.getSession();

        // session에 "read_list"라는 이름으로 저장된 객체를 얻어내자
        Object obj = session.getAttribute("read_list");
        ArrayList<BbsVO> list = null;
        if(obj == null){
            list = new ArrayList<>();
            session.setAttribute("read_list", list);
        } else
            list = (ArrayList<BbsVO>)obj;
        // obj의 주소를 list에 저장한다.

        BbsVO vo = BbsDAO.getBbs(b_idx); // 사용자가 선택한 게시물을 검색해 온다.
        // 검색된 vo가 처음으로 읽은 게시물인지? 판단하자
        if(checkBbs(list, vo)) {
            BbsDAO.hit(b_idx); // 조회수 증가
            list.add(vo); // list는 이미 HttpSession에 저장된 상태이므로 이후의 별도 작업은 필요없음
            // 위에서 obj의 주소를 list에 저장했으니 list.add를 하면 obj에 저장됌
        }


        request.setAttribute("vo",vo);
        //request.setAttribute("nowPage",cPage);
        return "view.jsp";
    }
}
