package emp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/index.jsp";
    }
}
