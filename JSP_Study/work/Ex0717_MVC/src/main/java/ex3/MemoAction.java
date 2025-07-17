package ex3;

import mybatis.dao.MemoDAO;
import mybatis.vo.MemoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemoAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 사원 목록 가져오기
        MemoVO[] ar = MemoDAO.getAll();

        // 얻어낸 목록을 request에 "emp"라는 이름으로 저장!
        request.setAttribute("memo", ar);

        return "ex3/memo.jsp";
    }
}
