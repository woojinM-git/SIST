package emp.action;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchAction2 implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String st = request.getParameter("searchType");
        String sv = request.getParameter("searchValue");

        System.out.println(st+":"+sv);

        EmpVO[] ar = EmpDAO.search(st, sv);

        request.setAttribute("ar_search", ar);
        System.out.println("SearchAction2: "+ar.length);

        return "jsp/search2.jsp";
    }
}
