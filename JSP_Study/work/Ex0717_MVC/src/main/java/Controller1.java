import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Controller1")
public class Controller1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // type이라는 파라미터를 받는다.
        String type = request.getParameter("type");

        // type이 null이거나 "greet"이면 view1.jsp로 경로를 지정한다.
        // 그렇지 않고 type의 값이 "hi" 이면 view2.jsp로 경로를 지정하자
        String viewPath = null; // JSP경로를 저장할 변수
        if(type == null || type.equalsIgnoreCase("greet")) {
            viewPath = "view1.jsp";
            request.setAttribute("v1", "Hello World!");
        } else if (type.equalsIgnoreCase("hi")) {
            viewPath = "view2.jsp";

            // 요청객체에 특저 객체를 저장할 수 있다.
            request.setAttribute("v1", "안녕하세요");
        }

        // MVC 패턴에서는 뷰 페이지 이동을 반드시 forward를 시킨다.
        RequestDispatcher disp = request.getRequestDispatcher(viewPath); // 내가 가고자 하는 도착지를 넣어줌
        disp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost에서 doGet을 호출하여 인자를 그대로 던져 post, get 둘 중 무엇을 호출하더라도 get으로 가게 했음
        doGet(request, response);
    }
}
