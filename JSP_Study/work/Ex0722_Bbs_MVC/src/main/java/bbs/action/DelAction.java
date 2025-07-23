package bbs.action;

import bbs.dao.BbsDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String viewPath = null;

        String b_idx = request.getParameter("b_idx");
        String cPage = request.getParameter("cPage");

        int cnt = BbsDAO.delBbs(b_idx); // 테이블에 접근해서 status를 바꿈
        viewPath = "Controller?type=list&cPage="+cPage;

        return viewPath;
    }
}
