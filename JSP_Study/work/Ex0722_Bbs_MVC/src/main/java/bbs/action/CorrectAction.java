package bbs.action;

import mybatis.vo.BbsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorrectAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("CorrectAction.execute()");

        String subject = request.getParameter("subject");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");
        String cPage = request.getParameter("cPage");
        String b_idx = request.getParameter("b_idx");
        System.out.println("b_idx : " + b_idx);

        BbsVO bvo = new BbsVO();
        bvo.setSubject(subject);
        bvo.setWriter(writer);
        bvo.setContent(content);
        bvo.setB_idx(b_idx);

        request.setAttribute("bvo", bvo);

        return "correct.jsp";
    }
}
