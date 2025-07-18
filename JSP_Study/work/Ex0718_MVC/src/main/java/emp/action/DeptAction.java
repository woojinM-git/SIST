package emp.action;

import mybatis.dao.DeptDAO;
import mybatis.vo.DeptVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeptAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 파라미터가 있다면 받으면 된다
        DeptVO[] ar = DeptDAO.getAll();

        if(ar != null)
            request.setAttribute("ar", ar); // jsp에서 ar이라고 지정해서 ar 로 만들어줘야함

        return "jsp/dept.jsp";
    }
}
