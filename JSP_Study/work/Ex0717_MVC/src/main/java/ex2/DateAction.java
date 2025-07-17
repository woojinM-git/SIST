package ex2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class DateAction {
    public String execute(HttpServletRequest request, HttpServletResponse response){
        // 현재 함수는 Servlet 이 아니지만 request와 response를 받아 동일하게 사용 가능
        LocalDate date = LocalDate.now();
        request.setAttribute("date", date.toString()); // 현재날짜를
        // forward될 request에 "date"라는 이름으로 저장!

        // 보여질 jsp경로를 반환하자!
        return "/ex2/page1.jsp";
    }
}
