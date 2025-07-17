package ex3;

import mybatis.dao.DeptDAO;
import mybatis.dao.EmpDAO;
import mybatis.vo.DeptVO;
import mybatis.vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeptAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 사원 목록 가져오기
        DeptVO[] ar = DeptDAO.getAll();

        // 얻어낸 목록을 request에 "emp"라는 이름으로 저장!
        request.setAttribute("dept", ar);

        return "ex3/dept.jsp";
    }
}
