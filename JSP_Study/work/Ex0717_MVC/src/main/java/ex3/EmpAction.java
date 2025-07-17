package ex3;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 사원 목록 가져오기
        EmpVO[] ar = EmpDAO.getAll();

        // 얻어낸 목록을 request에 "emp"라는 이름으로 저장!
        request.setAttribute("emp", ar);

        return "ex3/emp.jsp";
    }
}
