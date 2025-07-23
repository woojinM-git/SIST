package bbs.action;

import bbs.dao.BbsDAO;
import mybatis.vo.BbsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 파라미터 값 받기(b_idx)
        String b_idx = request.getParameter("b_idx"); // 기본키
        String cPage = request.getParameter("cPage"); // 원래 보던 페이지로 돌아가기 위해 필요함

        BbsVO vo = BbsDAO.getBbs(b_idx);

        BbsDAO.hit(b_idx);

        if(vo != null)
            request.setAttribute("vo", vo);

        return "view.jsp";
    }
}
